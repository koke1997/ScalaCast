package utils

import javax.inject._
import akka.actor.ActorSystem
import akka.stream.Materializer
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.ws.{Message, TextMessage}
import akka.http.scaladsl.server.Directives._
import akka.stream.scaladsl.Flow
import scala.concurrent.{Future, ExecutionContext}

@Singleton
class WebSocketUtil @Inject()(implicit system: ActorSystem, mat: Materializer, ec: ExecutionContext) {

  def startWebSocket(interface: String, port: Int): Future[Http.ServerBinding] = {
    val route = path("ws" / "video-stream") {
      handleWebSocketMessages(webSocketFlow)
    }
    Http().newServerAt(interface, port).bind(route)
  }

  def stopWebSocket(binding: Future[Http.ServerBinding]): Future[Unit] = {
    binding.flatMap(b => b.unbind().map(_ => ()))
  }

  private def webSocketFlow: Flow[Message, Message, Any] = {
    Flow[Message].map {
      case TextMessage.Strict(text) =>
        TextMessage.Strict(s"Received: $text")
      case _ =>
        TextMessage.Strict("Unsupported message type")
    }
  }
}

object WebSocketUtil {
  def startWebSocketServer(interface: String, port: Int)
      (implicit system: akka.actor.ActorSystem, ec: ExecutionContext): Future[Http.ServerBinding] = {
    Http().newServerAt(interface, port).bind(_ => ???)
  }
  
  def stopWebSocketServer(binding: Http.ServerBinding)
      (implicit ec: ExecutionContext): Future[Unit] = {
    binding.unbind().map(_ => ())
  }
}
