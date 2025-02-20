package utils

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AnalyticsUtil @Inject()(implicit ec: ExecutionContext) {

  def trackUsage(userId: String, action: String): Future[Unit] = Future {
    // Implement logic to track user usage
    println(s"Tracking usage for user $userId: $action")
  }

  def trackPerformance(metric: String, value: Double): Future[Unit] = Future {
    // Implement logic to track performance metrics
    println(s"Tracking performance metric $metric: $value")
  }
}
