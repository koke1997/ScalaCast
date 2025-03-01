package controllers

import javax.inject._
import play.api.mvc._
import scala.concurrent.ExecutionContext

@Singleton
class FrontendInterfaceController @Inject()(cc: ControllerComponents)(implicit ec: ExecutionContext) 
  extends AbstractController(cc) {
  
  def index() = Action {
    Ok("Frontend Interface Service")
  }
  
  def manageOptions() = Action {
    Ok("Options managed")
  }
  
  def manageMonitorings() = Action {
    Ok("Monitorings managed")
  }
  
  def manageScripts() = Action {
    Ok("Scripts managed")
  }
}