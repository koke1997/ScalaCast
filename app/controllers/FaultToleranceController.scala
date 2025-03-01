package controllers

import javax.inject._
import play.api.mvc._
import scala.concurrent.ExecutionContext

@Singleton
class FaultToleranceController @Inject()(cc: ControllerComponents)(implicit ec: ExecutionContext) 
  extends AbstractController(cc) {
  
  def index() = Action {
    Ok("Fault Tolerance Service")
  }
  
  def start() = Action {
    Ok("Started fault tolerance service")
  }
  
  def stop() = Action {
    Ok("Stopped fault tolerance service")
  }
}