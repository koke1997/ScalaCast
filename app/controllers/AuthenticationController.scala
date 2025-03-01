package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import services.AuthenticationService
import scala.concurrent.{ExecutionContext, Future}

// Add to all controllers
import scala.concurrent.ExecutionContext

@Singleton
class AuthenticationController @Inject()(cc: ControllerComponents, authService: AuthenticationService)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def login() = Action(parse.json) { request =>
    (request.body \ "username").asOpt[String].map { username =>
      Ok(Json.obj("token" -> "dummy-token"))
    }.getOrElse(BadRequest("Username required"))
  }

  def refreshToken() = Action {
    Ok(Json.obj("token" -> "refreshed-token"))
  }
}
