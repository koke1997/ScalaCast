package services

import scala.concurrent.{Future, ExecutionContext}
import javax.inject._

@Singleton
class WebSocketService @Inject()(implicit ec: ExecutionContext) {

  def startVideoStream(): Future[Unit] = Future {
    println("Starting video stream...")
    // Add logic to start video stream
  }
}
