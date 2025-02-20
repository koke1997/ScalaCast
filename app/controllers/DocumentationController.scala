package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class DocumentationController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def getApiDocs() = Action { implicit request: Request[AnyContent] =>
    Ok("API Documentation")
  }
}
