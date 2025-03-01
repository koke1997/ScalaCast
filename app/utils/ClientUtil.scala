package utils

import javax.inject._
import scala.concurrent.{Future, ExecutionContext}
import models.Client

@Singleton
class ClientUtil @Inject()(implicit ec: ExecutionContext) {
  
  def getClient(clientId: String): Future[Option[Client]] = {
    Future.successful(Some(Client(clientId, s"Client $clientId", s"client$clientId@example.com", "active")))
  }
  
  def updateClient(clientId: String, client: Client): Future[Boolean] = {
    Future.successful(true)
  }
}
