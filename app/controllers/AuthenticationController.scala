package controllers

import javax.inject._
import play.api.mvc._
import services.AuthenticationService
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AuthenticationController @Inject()(cc: ControllerComponents, authService: AuthenticationService)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def login: Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val json = request.body.asJson.get
    val username = (json \ "username").as[String]
    val password = (json \ "password").as[String]

    authService.login(username, password).map {
      case Some(token) => Ok(token)
      case None => Unauthorized("Invalid credentials")
    }
  }

  def refreshToken: Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val json = request.body.asJson.get
    val token = (json \ "token").as[String]

    authService.refreshToken(token).map {
      case Some(newToken) => Ok(newToken)
      case None => Unauthorized("Invalid token")
    }
  }
}
