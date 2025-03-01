package controllers

import javax.inject._
import play.api.mvc._
import play.api.Configuration

@Singleton
class HttpsRedirectController @Inject()(
  config: Configuration,
  cc: ControllerComponents
) extends AbstractController(cc) {
  
  private val httpsPort = config.get[Int]("play.server.https.port")
  
  def redirect(path: String) = Action { request =>
    val hostParts = request.host.split(":")
    val host = hostParts.headOption.getOrElse("localhost")
    
    Redirect(s"https://$host:$httpsPort/$path", 301)
      .withHeaders("Cache-Control" -> "no-cache")
  }
}