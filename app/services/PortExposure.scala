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
    }
  }

  def closePort(port: Int): Future[Unit] = Future {
    serverSockets.get(port) match {
      case Some(serverSocket) =>
        serverSocket.close()
        serverSockets -= port
        println(s"Port $port closed successfully.")
      case None =>
        println(s"Port $port is not currently exposed.")
    }
  }

  def exposePortsForEndUsers(ports: List[Int]): Future[Unit] = Future.sequence(ports.map(exposePort)).map(_ => ())

  def exposePortsForManualOperators(ports: List[Int]): Future[Unit] = Future.sequence(ports.map(exposePort)).map(_ => ())

  def closeAllPorts(): Future[Unit] = Future.sequence(serverSockets.keys.map(closePort)).map(_ => ())
}
