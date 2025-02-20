package services

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ConfigurationService @Inject()(implicit ec: ExecutionContext) {

  private var streamingQuality: String = "HD"

  def getStreamingQuality: Future[String] = Future {
    streamingQuality
  }

  def updateStreamingQuality(quality: String): Future[Unit] = Future {
    streamingQuality = quality
  }
}
