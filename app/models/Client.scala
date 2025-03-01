package models

import play.api.libs.json._

case class Client(
  id: String,
  name: String,
  email: String,
  status: String
)

object Client {
  implicit val format: Format[Client] = Json.format[Client]
}