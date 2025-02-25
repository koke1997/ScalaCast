package controllers

import javax.inject._
import play.api.mvc._
import services.PortExposureService
import scala.concurrent.{ExecutionContext, Future}
import play.api.libs.json.JsObject

@Singleton
class PortExposureController @Inject()(cc: ControllerComponents, portExposureService: PortExposureService)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def index() = Action.async { implicit request: Request[AnyContent] =>
    Future.successful(Ok("Port Exposure Controller"))
  }

  def exposePort(port: Int) = Action.async(parse.json) { implicit request: Request[AnyContent] =>
    portExposureService.exposePort(port).map { _ =>
      Ok(s"Port $port exposed successfully.")
    }.recover {
      case ex: Exception => InternalServerError(s"Error exposing port $port: ${ex.getMessage}")
    }
  }

  def closePort(port: Int) = Action.async(parse.json) { implicit request: Request[AnyContent] =>
    portExposureService.closePort(port).map { _ =>
      Ok(s"Port $port closed successfully.")
    }.recover {
      case ex: Exception => InternalServerError(s"Error closing port $port: ${ex.getMessage}")
    }
  }

  def exposePortsForEndUsers(ports: List[Int]) = Action.async(parse.json) { implicit request: Request[AnyContent] =>
    portExposureService.exposePortsForEndUsers(ports).map { _ =>
      Ok(s"Ports ${ports.mkString(", ")} exposed successfully for end users.")
    }.recover {
      case ex: Exception => InternalServerError(s"Error exposing ports for end users: ${ex.getMessage}")
    }
  }

  def exposePortsForManualOperators(ports: List[Int]) = Action.async(parse.json) { implicit request: Request[AnyContent] =>
    portExposureService.exposePortsForManualOperators(ports).map { _ =>
      Ok(s"Ports ${ports.mkString(", ")} exposed successfully for manual operators.")
    }.recover {
      case ex: Exception => InternalServerError(s"Error exposing ports for manual operators: ${ex.getMessage}")
    }
  }

  def closeAllPorts() = Action.async { implicit request: Request[AnyContent] =>
    portExposureService.closeAllPorts().map { _ =>
      Ok("All ports closed successfully.")
    }.recover {
      case ex: Exception => InternalServerError(s"Error closing all ports: ${ex.getMessage}")
    }
  }

  def batch() = Action.async(parse.json) { implicit request: Request[AnyContent] =>
    val operations = (request.body \ "operations").as[List[JsObject]]
    val futures = operations.map { operation =>
      val action = (operation \ "action").as[String]
      val port = (operation \ "port").as[Int]
      action match {
        case "expose" => portExposureService.exposePort(port)
        case "close" => portExposureService.closePort(port)
        case _ => Future.failed(new IllegalArgumentException(s"Unknown action: $action"))
      }
    }
    Future.sequence(futures).map { _ =>
      Ok("Batch operations completed successfully.")
    }.recover {
      case ex: Exception => InternalServerError(s"Error performing batch operations: ${ex.getMessage}")
    }
  }
}
