package services

import scala.concurrent.{Future, ExecutionContext}
import scala.util.{Success, Failure}
import java.net.{ServerSocket, Socket}
import java.io.{BufferedReader, InputStreamReader, PrintWriter}
import javax.inject._

@Singleton
class CentralizedServer @Inject()(implicit ec: ExecutionContext) {

  private var serverSocket: Option[ServerSocket] = None
  private var isRunning = false

  def startServer(port: Int): Future[Unit] = Future {
    if (isRunning) {
      restartServer(port)
    } else {
      try {
        serverSocket = Some(new ServerSocket(port))
        isRunning = true
        println(s"Centralized server started on port $port")

        while (isRunning) {
          val clientSocket = serverSocket.get.accept()
          handleClientRequest(clientSocket)
        }
      } catch {
        case e: Exception =>
          println(s"Error starting server: ${e.getMessage}")
          stopServer()
          retryServerStart(port)
      }
    }
  }

  def stopServer(): Future[Unit] = Future {
    isRunning = false
    serverSocket.foreach(_.close())
    println("Centralized server stopped")
  }

  def handleClientRequest(clientSocket: Socket): Future[Unit] = Future {
    try {
      val in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream))
      val out = new PrintWriter(clientSocket.getOutputStream, true)

      val request = in.readLine()
      println(s"Received request: $request")

      // Handle the request and send a response
      val response = s"Response to: $request"
      out.println(response)

      clientSocket.close()
    } catch {
      case e: Exception =>
        println(s"Error handling client request: ${e.getMessage}")
        clientSocket.close()
    }
  }

  def restartServer(port: Int): Future[Unit] = {
    for {
      _ <- stopServer()
      _ <- startServer(port)
    } yield ()
  }

  private def retryServerStart(port: Int, retryCount: Int = 0): Future[Unit] = {
    if (retryCount < 3) {
      println(s"Retrying server start... Attempt ${retryCount + 1}")
      startServer(port).recoverWith {
        case _ => retryServerStart(port, retryCount + 1)
      }
    } else {
      Future.failed(new Exception("Failed to start server after 3 attempts"))
    }
  }

  def getStatus: Future[String] = Future.successful("Running")
}
