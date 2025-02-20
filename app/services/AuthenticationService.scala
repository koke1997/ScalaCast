package services

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}
import utils.TokenUtil

@Singleton
class AuthenticationService @Inject()(implicit ec: ExecutionContext) {

  def login(username: String, password: String): Future[Option[String]] = Future {
    // Placeholder for actual authentication logic
    if (username == "admin" && password == "password") {
      Some(TokenUtil.generateToken(username))
    } else {
      None
    }
  }

  def refreshToken(token: String): Future[Option[String]] = Future {
    // Placeholder for actual token validation logic
    if (TokenUtil.validateToken(token)) {
      Some(TokenUtil.generateToken("admin")) // Placeholder for actual user extraction from token
    } else {
      None
    }
  }
}
