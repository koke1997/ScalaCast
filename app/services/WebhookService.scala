package services

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}
import models.Webhook
import play.api.libs.json.Json

@Singleton
class WebhookService @Inject()(implicit ec: ExecutionContext) {

  private var webhooks: Map[String, Webhook] = Map.empty

  def createWebhook(url: String): Future[String] = Future {
    val webhookId = java.util.UUID.randomUUID().toString
    val webhook = Webhook(webhookId, url)
    webhooks += (webhookId -> webhook)
    webhookId
  }

  def getWebhookById(webhookId: String): Future[Option[Webhook]] = Future {
    webhooks.get(webhookId)
  }

  def deleteWebhook(webhookId: String): Future[Unit] = Future {
    webhooks -= webhookId
  }
}
