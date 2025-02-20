package services

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}

case class Client(id: String, name: String, email: String)

@Singleton
class ClientManagementService @Inject()(implicit ec: ExecutionContext) {

  private var clients: List[Client] = List(
    Client("1", "John Doe", "john.doe@example.com"),
    Client("2", "Jane Smith", "jane.smith@example.com")
  )

  def getClients: Future[List[Client]] = Future {
    clients
  }

  def getClientById(clientId: String): Future[Option[Client]] = Future {
    clients.find(_.id == clientId)
  }
}
