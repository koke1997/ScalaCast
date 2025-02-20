package services

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}
import utils.HealthCheckUtil

@Singleton
class HealthCheckService @Inject()(implicit ec: ExecutionContext) {

  def getHealthStatus: Future[String] = Future {
    HealthCheckUtil.checkHealth()
  }

  def getMetrics: Future[String] = Future {
    // Placeholder for actual metrics logic
    "Metrics data"
  }
}
