package services

import scala.concurrent.{Future, ExecutionContext}
import scala.util.{Success, Failure}
import java.net.ServerSocket

class PortExposureService @Inject()(implicit ec: ExecutionContext) {

  private var serverSockets: Map[Int, ServerSocket] = Map.empty

  def exposePort(port: Int): Future[Unit] = Future {
    try {
      val serverSocket = new ServerSocket(port)
      serverSockets += (port -> serverSocket)
      println(s"Port $port exposed successfully.")
    } catch {
      case e: Exception =>
        println(s"Error exposing port $port: ${e.getMessage}")
        retryExposePort(port)
    }
  }

  def closePort(port: Int): Future[Unit] = Future {
    serverSockets.get(port) match {
      case Some(serverSocket) =>
        try {
          serverSocket.close()
          serverSockets -= port
          println(s"Port $port closed successfully.")
        } catch {
          case e: Exception =>
            println(s"Error closing port $port: ${e.getMessage}")
        }
      case None =>
        println(s"Port $port is not currently exposed.")
    }
  }

  def exposePortsForEndUsers(ports: List[Int]): Future[Unit] = Future.sequence(ports.map(exposePort)).map(_ => ())

  def exposePortsForManualOperators(ports: List[Int]): Future[Unit] = Future.sequence(ports.map(exposePort)).map(_ => ())

  def closeAllPorts(): Future[Unit] = Future.sequence(serverSockets.keys.map(closePort)).map(_ => ())

  private def retryExposePort(port: Int, retryCount: Int = 0): Future[Unit] = {
    if (retryCount < 3) {
      println(s"Retrying port exposure... Attempt ${retryCount + 1}")
      exposePort(port).recoverWith {
        case _ => retryExposePort(port, retryCount + 1)
      }
    } else {
      Future.failed(new Exception(s"Failed to expose port $port after 3 attempts"))
    }
  }
}
