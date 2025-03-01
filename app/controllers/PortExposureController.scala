package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import services.PortExposureService
import scala.concurrent.{Future, ExecutionContext}

@Singleton
class PortExposureController @Inject()(cc: ControllerComponents, portExposureService: PortExposureService)
  (implicit ec: ExecutionContext) extends AbstractController(cc) {
  
  def index() = Action {
    Ok("Port Exposure Service")
  }
  
  def exposePort() = Action.async(parse.json) { implicit request =>
    (request.body \ "port").asOpt[Int].map { port =>
      portExposureService.exposePort(port).map(_ => Ok)
    }.getOrElse(Future.successful(BadRequest("Port required")))
  }
  
  def closePort() = Action.async(parse.json) { implicit request =>
    (request.body \ "port").asOpt[Int].map { port =>
      portExposureService.closePort(port).map(_ => Ok)
    }.getOrElse(Future.successful(BadRequest("Port required")))
  }
  
  def exposePortsForEndUsers() = Action.async(parse.json) { implicit request =>
    (request.body \ "ports").asOpt[List[Int]].map { ports =>
      portExposureService.exposePortsForEndUsers(ports).map(_ => Ok)
    }.getOrElse(Future.successful(BadRequest("Ports required")))
  }
  
  def exposePortsForManualOperators() = Action.async(parse.json) { implicit request =>
    (request.body \ "ports").asOpt[List[Int]].map { ports =>
      portExposureService.exposePortsForManualOperators(ports).map(_ => Ok)
    }.getOrElse(Future.successful(BadRequest("Ports required")))
  }
  
  def closeAllPorts() = Action.async {
    portExposureService.closeAllPorts().map(_ => Ok)
  }
  
  def batch() = Action.async(parse.json) { implicit request =>
    (request.body \ "operations").asOpt[List[JsObject]].map { operations =>
      portExposureService.batch(operations).map(_ => Ok)
    }.getOrElse(Future.successful(BadRequest("Operations required")))
  }
}
