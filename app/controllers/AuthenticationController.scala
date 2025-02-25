package controllers

import javax.inject._
import play.api.mvc._
import services.AuthenticationService
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AuthenticationController @Inject()(cc: ControllerComponents, authService: AuthenticationService)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def login(username: String, password: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    authService.login(username, password).map {
      case Some(token) => Ok(token)
      case None => Unauthorized("Invalid credentials")
    }
  }

  def refreshToken(token: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    authService.refreshToken(token).map {
      case Some(newToken) => Ok(newToken)
      case None => Unauthorized("Invalid token")
    }
  }
}
