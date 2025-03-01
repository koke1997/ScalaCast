package filters

import javax.inject._
import play.api.http.HttpErrorHandler
import play.api.mvc._
import play.api.libs.json._
import scala.concurrent.Future
import play.api.mvc.Results._

@Singleton
class ErrorHandler @Inject()() extends HttpErrorHandler {
  
  def onClientError(request: RequestHeader, statusCode: Int, message: String): Future[Result] = {
    Future.successful(
      Status(statusCode)(Json.obj(
        "error" -> message,
        "status" -> statusCode
      ))
    )
  }
  
  def onServerError(request: RequestHeader, exception: Throwable): Future[Result] = {
    Future.successful(
      InternalServerError(Json.obj(
        "error" -> s"A server error occurred: ${exception.getMessage}"
      ))
    )
  }
}
