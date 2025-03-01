package models

import play.api.libs.json._

case class Peer(
  id: String,
  address: String,
  port: Int,
  status: String
)

object Peer {
  implicit val format: Format[Peer] = Json.format[Peer]
}