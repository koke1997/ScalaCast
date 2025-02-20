package services

import java.io.FileInputStream
import java.security.KeyStore
import javax.net.ssl.{KeyManagerFactory, SSLContext, TrustManagerFactory}
import scala.util.{Try, Success, Failure}

object SSLConfiguration {

  def loadKeyStore(path: String, password: String, keyStoreType: String): KeyStore = {
    val keyStore = KeyStore.getInstance(keyStoreType)
    val keyStoreStream = new FileInputStream(path)
    keyStore.load(keyStoreStream, password.toCharArray)
    keyStoreStream.close()
    keyStore
  }

  def createSSLContext(keyStorePath: String, keyStorePassword: String, keyStoreType: String,
                       trustStorePath: String, trustStorePassword: String, trustStoreType: String): SSLContext = {
    val keyStore = loadKeyStore(keyStorePath, keyStorePassword, keyStoreType)
    val trustStore = loadKeyStore(trustStorePath, trustStorePassword, trustStoreType)

    val keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm)
    keyManagerFactory.init(keyStore, keyStorePassword.toCharArray)

    val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm)
    trustManagerFactory.init(trustStore)

    val sslContext = SSLContext.getInstance("TLS")
    sslContext.init(keyManagerFactory.getKeyManagers, trustManagerFactory.getTrustManagers, null)
    sslContext
  }

  def applySSLContext(sslContext: SSLContext): Unit = {
    SSLContext.setDefault(sslContext)
  }

  def configureSSL(keyStorePath: String, keyStorePassword: String, keyStoreType: String,
                   trustStorePath: String, trustStorePassword: String, trustStoreType: String): Try[Unit] = {
    Try {
      val sslContext = createSSLContext(keyStorePath, keyStorePassword, keyStoreType, trustStorePath, trustStorePassword, trustStoreType)
      applySSLContext(sslContext)
      println("SSL context successfully created and applied.")
    } match {
      case Success(_) => Success(())
      case Failure(exception) =>
        println(s"Error configuring SSL: ${exception.getMessage}")
        retrySSLConfiguration(keyStorePath, keyStorePassword, keyStoreType, trustStorePath, trustStorePassword, trustStoreType)
    }
  }

  private def retrySSLConfiguration(keyStorePath: String, keyStorePassword: String, keyStoreType: String,
                                    trustStorePath: String, trustStorePassword: String, trustStoreType: String, retryCount: Int = 0): Try[Unit] = {
    if (retryCount < 3) {
      println(s"Retrying SSL configuration... Attempt ${retryCount + 1}")
      configureSSL(keyStorePath, keyStorePassword, keyStoreType, trustStorePath, trustStorePassword, trustStoreType).recoverWith {
        case _ => retrySSLConfiguration(keyStorePath, keyStorePassword, keyStoreType, trustStorePath, trustStorePassword, trustStoreType, retryCount + 1)
      }
    } else {
      Failure(new Exception("Failed to configure SSL after 3 attempts"))
    }
  }
}
