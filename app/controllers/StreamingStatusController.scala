package controllers

import javax.inject._
import play.api.mvc._
import services.StreamingStatusService

@Singleton
class StreamingStatusController @Inject()(cc: ControllerComponents, streamingStatusService: StreamingStatusService) extends AbstractController(cc) {

  def getStatus(streamId: String) = Action.async { implicit request: Request[AnyContent] =>
    streamingStatusService.getStatus(streamId).map { status =>
      Ok(status)
    }.recover {
      case ex: Exception => InternalServerError(ex.getMessage)
    }
  }
}
