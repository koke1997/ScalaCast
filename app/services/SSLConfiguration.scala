package services

import java.io.FileInputStream
import java.security.KeyStore
import javax.net.ssl.{KeyManagerFactory, SSLContext, TrustManagerFactory}

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
}
