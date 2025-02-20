package utils

import play.api.mvc.Result
import play.api.mvc.Results._
import play.api.libs.json.Json

object ErrorUtil {

  def handleError(statusCode: Int, message: String): Result = {
    Status(statusCode)(Json.obj(
      "status" -> "error",
      "message" -> message
    ))
  }
}
