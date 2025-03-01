package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import scala.concurrent.ExecutionContext

@Singleton
class AnalyticsController @Inject()(cc: ControllerComponents)(implicit ec: ExecutionContext) 
  extends AbstractController(cc) {

  def getUsage() = Action {
    Ok(Json.obj("usage" -> "analytics data"))
  }

  def getPerformance() = Action {
    Ok(Json.obj("performance" -> "metrics data"))
  }
}
