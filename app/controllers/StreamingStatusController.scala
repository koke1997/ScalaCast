package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import services.StreamingStatusService
import scala.concurrent.ExecutionContext

@Singleton
class StreamingStatusController @Inject()(cc: ControllerComponents, 
                                         streamingStatusService: StreamingStatusService)
                                        (implicit ec: ExecutionContext) extends AbstractController(cc) {

  def getStatus(streamId: String) = Action.async { implicit request =>
    streamingStatusService.getStatus(streamId).map { status =>
      Ok(Json.obj("status" -> status))
    }.recover {
      case ex: Exception => InternalServerError(Json.obj("error" -> ex.getMessage))
    }
  }
}
