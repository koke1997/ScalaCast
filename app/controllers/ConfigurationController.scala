package controllers

import javax.inject._
import play.api.mvc._
import services.ConfigurationService
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ConfigurationController @Inject()(cc: ControllerComponents, configurationService: ConfigurationService)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def getStreamingQuality: Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    configurationService.getStreamingQuality.map { quality =>
      Ok(quality)
    }
  }

  def updateStreamingQuality: Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    request.body.asJson.map { json =>
      (json \ "quality").asOpt[String].map { quality =>
        configurationService.updateStreamingQuality(quality).map { _ =>
          Ok("Streaming quality updated")
        }
      }.getOrElse(Future.successful(BadRequest("Missing parameter [quality]")))
    }.getOrElse(Future.successful(BadRequest("Expecting JSON data")))
  }
}
