package controllers

import javax.inject._
import play.api.mvc._
import services.AnalyticsService

@Singleton
class AnalyticsController @Inject()(cc: ControllerComponents, analyticsService: AnalyticsService) extends AbstractController(cc) {

  def getUsage() = Action { implicit request: Request[AnyContent] =>
    val usageData = analyticsService.getUsage()
    Ok(usageData)
  }

  def getPerformance() = Action { implicit request: Request[AnyContent] =>
    val performanceData = analyticsService.getPerformance()
    Ok(performanceData)
  }
}
