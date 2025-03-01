package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import services.PeerDiscovery
import scala.concurrent.{Future, ExecutionContext}

@Singleton
class PeerDiscoveryController @Inject()(cc: ControllerComponents)
                                       (implicit ec: ExecutionContext) extends AbstractController(cc) {
  
  def index() = Action.async {
    PeerDiscovery.discoverPeers().map { peers =>
      Ok(Json.toJson(peers))
    }
  }
  
  def start() = Action.async {
    PeerDiscovery.start().map(_ => 
      Ok(Json.obj("status" -> "Peer Discovery Started"))
    )
  }
  
  def stop() = Action.async {
    PeerDiscovery.stop().map(_ =>
      Ok(Json.obj("status" -> "Peer Discovery Stopped"))
    )
  }
  
  def handleMessage() = Action.async(parse.json) { request =>
    (request.body \ "message").asOpt[String] match {
      case Some(message) =>
        PeerDiscovery.handleMessage(message).map(_ => 
          Ok(Json.obj("status" -> "Message handled"))
        )
      case None =>
        Future.successful(BadRequest(Json.obj("error" -> "Message required")))
    }
  }
  
  def broadcastMessage() = Action.async(parse.json) { request =>
    (request.body \ "message").asOpt[String] match {
      case Some(message) =>
        PeerDiscovery.broadcastMessage(message).map(_ => 
          Ok(Json.obj("status" -> "Message broadcast"))
        )
      case None =>
        Future.successful(BadRequest(Json.obj("error" -> "Message required")))
    }
  }
}