package controllers

import javax.inject._
import play.api.mvc._
import services.ClientManagementService
import scala.concurrent.ExecutionContext

@Singleton
class ClientManagementController @Inject()(cc: ControllerComponents, clientManagementService: ClientManagementService)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def getClients = Action.async { implicit request: Request[AnyContent] =>
    clientManagementService.getClients.map { clients =>
      Ok(clients.toString)
    }
  }

  def getClientById(clientId: String) = Action.async { implicit request: Request[AnyContent] =>
    clientManagementService.getClientById(clientId).map {
      case Some(client) => Ok(client.toString)
      case None => NotFound(s"Client with ID $clientId not found")
    }
  }
}
