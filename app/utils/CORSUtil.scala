package utils

import play.api.mvc.{RequestHeader, Result}
import scala.concurrent.Future

object CORSUtil {
  def applyCORS(result: Result): Result = {
    result.withHeaders(
      "Access-Control-Allow-Origin" -> "*",
      "Access-Control-Allow-Methods" -> "GET, POST, PUT, DELETE, OPTIONS",
      "Access-Control-Allow-Headers" -> "Content-Type, Authorization"
    )
  }

  def handlePreflight(request: RequestHeader): Future[Result] = {
    Future.successful(
      Results.Ok.withHeaders(
        "Access-Control-Allow-Origin" -> "*",
        "Access-Control-Allow-Methods" -> "GET, POST, PUT, DELETE, OPTIONS",
        "Access-Control-Allow-Headers" -> "Content-Type, Authorization"
      )
    )
  }
}
