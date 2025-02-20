package services

import scala.concurrent.{Future, ExecutionContext}
import scala.concurrent.duration._
import scala.util.{Success, Failure}

object PeerDiscovery {
  implicit val ec: ExecutionContext = ExecutionContext.global

  private var isRunning = false
  private var peers: Set[String] = Set.empty

  def start(): Future[Unit] = Future {
    if (isRunning) {
      restart()
    } else {
      isRunning = true
      println("Peer discovery started.")
      // Simulate peer discovery process
      while (isRunning) {
        Thread.sleep(5000)
        broadcastMessage("Hello, peers!")
      }
    }
  }.recoverWith {
    case e: Exception =>
      println(s"Error starting peer discovery: ${e.getMessage}")
      retryPeerDiscoveryStart()
  }

  def stop(): Future[Unit] = Future {
    isRunning = false
    println("Peer discovery stopped.")
  }

  def handleMessage(message: String): Future[Unit] = Future {
    try {
      println(s"Received peer discovery message: $message")
      // Handle incoming peer discovery message
      peers += message
    } catch {
      case e: Exception =>
        println(s"Error handling peer discovery message: ${e.getMessage}")
    }
  }

  def broadcastMessage(message: String): Future[Unit] = Future {
    println(s"Broadcasting peer discovery message: $message")
    // Broadcast peer discovery message to all peers
    peers.foreach(peer => println(s"Message sent to $peer: $message"))
  }.recoverWith {
    case e: Exception =>
      println(s"Error broadcasting peer discovery message: ${e.getMessage}")
      retryBroadcastMessage(message)
  }

  def restart(): Future[Unit] = {
    for {
      _ <- stop()
      _ <- start()
    } yield ()
  }

  private def retryPeerDiscoveryStart(retryCount: Int = 0): Future[Unit] = {
    if (retryCount < 3) {
      println(s"Retrying peer discovery start... Attempt ${retryCount + 1}")
      start().recoverWith {
        case _ => retryPeerDiscoveryStart(retryCount + 1)
      }
    } else {
      Future.failed(new Exception("Failed to start peer discovery after 3 attempts"))
    }
  }

  private def retryBroadcastMessage(message: String, retryCount: Int = 0): Future[Unit] = {
    if (retryCount < 3) {
      println(s"Retrying broadcast message... Attempt ${retryCount + 1}")
      broadcastMessage(message).recoverWith {
        case _ => retryBroadcastMessage(message, retryCount + 1)
      }
    } else {
      Future.failed(new Exception("Failed to broadcast message after 3 attempts"))
    }
  }
}
