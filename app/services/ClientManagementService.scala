package services

import javax.inject._
import scala.concurrent.{Future, ExecutionContext}
import models.Client

@Singleton
class ClientManagementService @Inject()(implicit ec: ExecutionContext) {

  def getClients: Future[List[Client]] = {
    Future.successful(List(
      Client("1", "Client 1", "client1@example.com", "active"),
      Client("2", "Client 2", "client2@example.com", "inactive")
    ))
  }
  
  def getClientById(id: String): Future[Option[Client]] = {
    Future.successful(Some(Client(id, s"Client $id", s"client$id@example.com", "active")))
  }
}
