package services

import javax.inject._
import scala.concurrent.{Future, ExecutionContext}
import play.api.Logger

@Singleton
class CentralizedServerService @Inject()(implicit ec: ExecutionContext) {
  
  private val logger = Logger(this.getClass)
  private var serverRunning = false
  private var serverPort: Option[Int] = None
  
  def startServer(port: Int): Future[Unit] = Future {
    logger.info(s"Starting centralized server on port $port")
    serverRunning = true
    serverPort = Some(port)
  }
  
  def stopServer(): Future[Unit] = Future {
    logger.info("Stopping centralized server")
    serverRunning = false
    serverPort = None
  }
  
  def isRunning: Boolean = serverRunning
  
  def getPort: Option[Int] = serverPort
}