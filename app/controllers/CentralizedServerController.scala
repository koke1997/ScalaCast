package controllers

import javax.inject._
import play.api.mvc._
import services.CentralizedServer
import scala.concurrent.ExecutionContext

@Singleton
class CentralizedServerController @Inject()(cc: ControllerComponents, centralizedServer: CentralizedServer)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def index() = Action.async { implicit request: Request[AnyContent] =>
    centralizedServer.getStatus.map { status =>
      Ok(status)
    }
  }

  def startServer(port: Int) = Action.async { implicit request: Request[AnyContent] =>
    centralizedServer.startServer(port).map { _ =>
      Ok("Server started")
    }.recover {
      case ex: Exception => InternalServerError(ex.getMessage)
    }
  }

  def stopServer() = Action.async { implicit request: Request[AnyContent] =>
    centralizedServer.stopServer().map { _ =>
      Ok("Server stopped")
    }.recover {
      case ex: Exception => InternalServerError(ex.getMessage)
    }
  }
}
