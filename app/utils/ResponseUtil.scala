package utils

import play.api.libs.json.{Json, JsValue}
import play.api.mvc.{Result, Results}

object ResponseUtil {

  def success(message: String, data: JsValue = Json.obj()): Result = {
    Results.Ok(Json.obj(
      "status" -> "success",
      "message" -> message,
      "data" -> data
    ))
  }

  def error(message: String, code: Int = 400): Result = {
    Results.Status(code)(Json.obj(
      "status" -> "error",
      "message" -> message
    ))
  }
}
