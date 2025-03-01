package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import scala.concurrent.ExecutionContext

@Singleton
class VideoStreamingController @Inject()(cc: ControllerComponents)(implicit ec: ExecutionContext) 
  extends AbstractController(cc) {
  
  def index() = Action {
    Ok(Json.obj("status" -> "Video Streaming Service"))
  }
}