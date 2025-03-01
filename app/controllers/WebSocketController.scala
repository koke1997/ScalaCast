package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.streams.ActorFlow
import akka.actor.ActorSystem
import akka.stream.Materializer
import akka.actor._
import scala.concurrent.ExecutionContext


@Singleton
class WebSocketController @Inject()(cc: ControllerComponents)(implicit system: ActorSystem, mat: Materializer) extends AbstractController(cc) {

  def videoStream: WebSocket = WebSocket.accept[String, String] { request =>
    ActorFlow.actorRef { out =>
      WebSocketActor.props(out)
    }
  }
}

object WebSocketActor {
  def props(out: ActorRef): Props = Props(new WebSocketActor(out))
}

class WebSocketActor(out: ActorRef) extends Actor {
  def receive: Receive = {
    case msg: String =>
      out ! s"Received: $msg"
  }
}
