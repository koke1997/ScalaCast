package services

import scala.concurrent.{Future, ExecutionContext}
import javax.inject._

@Singleton
class StreamingStatusService @Inject()(implicit ec: ExecutionContext) {

  def getStatus(streamId: String): Future[String] = Future {
    // Simulate fetching streaming status from a data source
    val status = s"Status of stream $streamId: Active"
    status
  }
}
