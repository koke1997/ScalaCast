package controllers

import javax.inject._
import play.api.mvc._
import scala.concurrent.ExecutionContext

@Singleton
class MonitoringController @Inject()(cc: ControllerComponents)(implicit ec: ExecutionContext) 
  extends AbstractController(cc) {
  
  def index() = Action {
    Ok("Monitoring Service")
  }
  
  def monitorCPU() = Action {
    Ok("Monitoring CPU")
  }
  
  def monitorMemory() = Action {
    Ok("Monitoring memory")
  }
  
  def monitorLogs() = Action {
    Ok("Monitoring logs")
  }
  
  def restartService() = Action {
    Ok("Service restarted")
  }
}