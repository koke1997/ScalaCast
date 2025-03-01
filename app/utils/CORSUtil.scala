package utils

import javax.inject._
import play.api.mvc._
import scala.concurrent.{Future, ExecutionContext}
import play.api.mvc.Results._

@Singleton
class CORSUtil @Inject()(implicit ec: ExecutionContext) {
  
  def addCORSHeaders(result: Result): Result = {
    result.withHeaders(
      "Access-Control-Allow-Origin" -> "*",
      "Access-Control-Allow-Methods" -> "GET, POST, OPTIONS, PUT, DELETE",
      "Access-Control-Allow-Headers" -> "Accept, Content-Type, Origin"
    )
  }
}
