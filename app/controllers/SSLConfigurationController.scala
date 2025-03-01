package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import scala.concurrent.ExecutionContext

@Singleton
class SSLConfigurationController @Inject()(cc: ControllerComponents)(implicit ec: ExecutionContext) 
  extends AbstractController(cc) {
  
  def index() = Action {
    Ok(Json.obj("status" -> "SSL Configuration Service"))
  }
}