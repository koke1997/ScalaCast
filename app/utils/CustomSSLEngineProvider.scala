package utils

import javax.net.ssl._
import play.server.api.SSLEngineProvider
import java.security._
import java.io.{FileInputStream, File}
import play.api.Configuration
import javax.inject._
import play.api.Logger

@Singleton
class CustomSSLEngineProvider @Inject()(configuration: Configuration) extends SSLEngineProvider {
  private val logger = Logger(getClass)
  
  // Implementation of the required sslContext method
  override def sslContext(): SSLContext = {
    try {
      val keyStorePath = configuration.get[String]("play.server.https.keyStore.path")
      val keyStorePassword = configuration.get[String]("play.server.https.keyStore.password")
      val keyStoreType = configuration.get[String]("play.server.https.keyStore.type")
      
      logger.info(s"Loading keystore from $keyStorePath")
      
      val keyStore = KeyStore.getInstance(keyStoreType)
      val keyStoreFile = new File(keyStorePath)
      
      if (!keyStoreFile.exists) {
        throw new IllegalStateException(s"Keystore file not found: $keyStorePath")
      }
      
      val inputStream = new FileInputStream(keyStoreFile)
      try {
        keyStore.load(inputStream, keyStorePassword.toCharArray)
      } finally {
        inputStream.close()
      }
      
      val keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm)
      keyManagerFactory.init(keyStore, keyStorePassword.toCharArray)
      
      val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm)
      trustManagerFactory.init(keyStore)
      
      val sslContext = SSLContext.getInstance("TLS")
      sslContext.init(keyManagerFactory.getKeyManagers, trustManagerFactory.getTrustManagers, null)
      sslContext
    } catch {
      case e: Exception =>
        logger.error("Failed to create SSL context", e)
        throw e
    }
  }
  
  override def createSSLEngine(): SSLEngine = {
    val engine = sslContext().createSSLEngine()
    engine.setUseClientMode(false)
    engine
  }
}