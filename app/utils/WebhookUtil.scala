package utils

import models.Webhook
import scala.concurrent.{ExecutionContext, Future}
import java.util.UUID

object WebhookUtil {

  def createWebhook(url: String)(implicit ec: ExecutionContext): Future[String] = Future {
    val webhookId = UUID.randomUUID().toString
    val webhook = Webhook(webhookId, url)
    // Save webhook to storage (e.g., database)
    webhookId
  }

  def getWebhook(webhookId: String)(implicit ec: ExecutionContext): Future[Option[Webhook]] = Future {
    // Retrieve webhook from storage (e.g., database)
    Some(Webhook(webhookId, "http://example.com"))
  }

  def deleteWebhook(webhookId: String)(implicit ec: ExecutionContext): Future[Unit] = Future {
    // Delete webhook from storage (e.g., database)
  }
}
