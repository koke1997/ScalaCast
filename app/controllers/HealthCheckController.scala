package controllers

import javax.inject._
import play.api.mvc._
import services.HealthCheckService
import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.ExecutionContext


@Singleton
class HealthCheckController @Inject()(cc: ControllerComponents, healthCheckService: HealthCheckService)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def health: Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    healthCheckService.getHealthStatus.map { status =>
      Ok(status)
    }
  }

  def metrics: Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    healthCheckService.getMetrics.map { metrics =>
      Ok(metrics)
    }
  }
}
