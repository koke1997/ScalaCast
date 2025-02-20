package services

import scala.concurrent.{Future, ExecutionContext}
import scala.util.{Success, Failure}

object FaultTolerance {
  implicit val ec: ExecutionContext = ExecutionContext.global

  private var isRunning = false
  private var errorCount = 0

  def start(): Future[Unit] = Future {
    if (isRunning) {
      restart()
    } else {
      isRunning = true
      println("Fault tolerance service started.")
    }
  }

  def stop(): Future[Unit] = Future {
    isRunning = false
    println("Fault tolerance service stopped.")
  }

  def handleError(error: String): Future[Unit] = Future {
    println(s"Handling error: $error")
    errorCount += 1
    // Add detailed error handling logic
    error match {
      case "Critical" => 
        println("Critical error occurred. Taking necessary actions.")
        retryCriticalError()
      case "Warning" => println("Warning: Please check the system.")
      case _ => println("Unknown error type.")
    }
  }

  def recover(error: String): Future[Unit] = Future {
    println(s"Recovering from error: $error")
    errorCount -= 1
    // Add recovery mechanisms
    if (errorCount < 0) errorCount = 0
    println("System recovered successfully.")
  }

  def restart(): Future[Unit] = {
    for {
      _ <- stop()
      _ <- start()
    } yield ()
  }

  private def retryCriticalError(retryCount: Int = 0): Future[Unit] = {
    if (retryCount < 3) {
      println(s"Retrying critical error recovery... Attempt ${retryCount + 1}")
      handleError("Critical").recoverWith {
        case _ => retryCriticalError(retryCount + 1)
      }
    } else {
      Future.failed(new Exception("Failed to recover from critical error after 3 attempts"))
    }
  }
}
