package filters

import javax.inject._
import play.api.mvc._
import play.api.http.HttpErrorHandler
import play.api.libs.json.Json
import scala.concurrent._

@Singleton
class ErrorHandler @Inject()(implicit ec: ExecutionContext) extends HttpErrorHandler {

  def onClientError(request: RequestHeader, statusCode: Int, message: String): Future[Result] = {
    Future.successful {
      Status(statusCode)(Json.obj(
        "status" -> statusCode,
        "error" -> message
      ))
    }
  }

  def onServerError(request: RequestHeader, exception: Throwable): Future[Result] = {
    Future.successful {
      InternalServerError(Json.obj(
        "status" -> 500,
        "error" -> "A server error occurred: " + exception.getMessage
      ))
    }
  }
}
