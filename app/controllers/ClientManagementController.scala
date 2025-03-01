package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import services.ClientManagementService
import scala.concurrent.ExecutionContext

@Singleton
class ClientManagementController @Inject()(cc: ControllerComponents, clientManagementService: ClientManagementService)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def getClients = Action.async { implicit request: Request[AnyContent] =>
    clientManagementService.getClients.map { clients =>
      Ok(Json.toJson(clients))
    }
  }

  def getClientById(clientId: String) = Action.async { implicit request: Request[AnyContent] =>
    clientManagementService.getClientById(clientId).map {
      case Some(client) => Ok(Json.toJson(client))
      case None => NotFound(Json.obj("error" -> s"Client with ID $clientId not found"))
    }
  }
}
