package controllers

import javax.inject._
import play.api.mvc._
import services.WebhookService
import scala.concurrent.{ExecutionContext, Future}
import play.api.libs.json.Json


@Singleton
class WebhookController @Inject()(cc: ControllerComponents, webhookService: WebhookService)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def createWebhook() = Action.async(parse.json) { request =>
    val webhookUrl = (request.body \ "url").as[String]
    webhookService.createWebhook(webhookUrl).map { webhookId =>
      Created(Json.obj("webhookId" -> webhookId))
    }.recover {
      case ex: Exception => InternalServerError(Json.obj("error" -> ex.getMessage))
    }
  }

  def getWebhookById(webhookId: String) = Action.async { implicit request: Request[AnyContent] =>
    webhookService.getWebhookById(webhookId).map {
      case Some(webhook) => Ok(Json.toJson(webhook))
      case None => NotFound(Json.obj("error" -> "Webhook not found"))
    }.recover {
      case ex: Exception => InternalServerError(Json.obj("error" -> ex.getMessage))
    }
  }

  def deleteWebhook(webhookId: String) = Action.async { implicit request: Request[AnyContent] =>
    webhookService.deleteWebhook(webhookId).map { _ =>
      NoContent
    }.recover {
      case ex: Exception => InternalServerError(Json.obj("error" -> ex.getMessage))
    }
  }
}
