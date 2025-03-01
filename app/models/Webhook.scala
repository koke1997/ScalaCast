package models

import play.api.libs.json._

case class Webhook(id: String, url: String)

object Webhook {
  implicit val format: Format[Webhook] = Json.format[Webhook]
}