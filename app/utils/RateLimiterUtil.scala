package utils

import scala.collection.mutable

object RateLimiterUtil {
  private val requestTimestamps = mutable.Map[String, mutable.Queue[Long]]()

  def isRateLimited(clientId: String, maxRequests: Int, timeWindow: Long): Boolean = {
    val currentTime = System.currentTimeMillis()
    val timestamps = requestTimestamps.getOrElseUpdate(clientId, mutable.Queue[Long]())
    timestamps.enqueue(currentTime)

    while (timestamps.nonEmpty && timestamps.head <= currentTime - timeWindow) {
      timestamps.dequeue()
    }

    timestamps.size > maxRequests
  }
}
