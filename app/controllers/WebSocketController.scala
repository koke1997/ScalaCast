package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.streams.ActorFlow
import akka.actor._
import play.api.libs.json._
import play.api.Logger
import akka.stream.Materializer

@Singleton
class WebSocketController @Inject()(cc: ControllerComponents)(implicit system: ActorSystem, mat: Materializer) extends AbstractController(cc) {
  private val logger = Logger(getClass)

  // Store for active connections
  private val connections = WebSocketActor.connections

  def videoStream: WebSocket = WebSocket.accept[JsValue, JsValue] { request =>
    logger.info(s"New WebSocket connection from ${request.remoteAddress}")
    ActorFlow.actorRef { out =>
      WebSocketActor.props(out)
    }
  }
}

object WebSocketActor {
  // Actor reference store for broadcasting
  val connections = collection.mutable.HashSet[ActorRef]()
  
  def props(out: ActorRef): Props = Props(new WebSocketActor(out))
}

class WebSocketActor(out: ActorRef) extends Actor {
  private val logger = Logger(getClass)
  
  override def preStart(): Unit = {
    WebSocketActor.connections += out
    logger.info(s"Client connected. Total connections: ${WebSocketActor.connections.size}")
  }
  
  override def postStop(): Unit = {
    WebSocketActor.connections -= out
    logger.info(s"Client disconnected. Remaining connections: ${WebSocketActor.connections.size}")
  }
  
  def receive: Receive = {
    case json: JsValue =>
      try {
        logger.info(s"Received message: ${Json.stringify(json)}")
        
        // Broadcast to all OTHER connections (not back to sender)
        WebSocketActor.connections.foreach { connection =>
          if (connection != out) {
            connection ! json
            logger.info(s"Forwarded message to another client")
          }
        }
      } catch {
        case e: Exception => 
          logger.error(s"Error processing message: ${e.getMessage}")
      }
      
    case msg =>
      logger.warn(s"Received unexpected message type: ${msg.getClass.getName}")
  }
}
