package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import services.CentralizedServerService
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class CentralizedServerController @Inject()(cc: ControllerComponents, 
                                           centralizedServerService: CentralizedServerService)
                                         (implicit ec: ExecutionContext) extends AbstractController(cc) {
  
  def index() = Action {
    Ok("Centralized Server Status")
  }
  
  def startServer() = Action.async(parse.json) { request =>
    (request.body \ "port").asOpt[Int].map { port =>
      centralizedServerService.startServer(port).map(_ => Ok(Json.obj("status" -> "started")))
    }.getOrElse(Future.successful(BadRequest("Port required")))
  }
  
  def stopServer() = Action.async {
    centralizedServerService.stopServer().map(_ => Ok(Json.obj("status" -> "stopped")))
  }
}
