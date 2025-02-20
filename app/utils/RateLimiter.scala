package utils

import scala.collection.mutable

class RateLimiter(maxRequests: Int, timeWindow: Long) {
  private val requestTimestamps = mutable.Queue[Long]()

  def isRateLimited: Boolean = {
    val currentTime = System.currentTimeMillis()
    requestTimestamps.enqueue(currentTime)

    while (requestTimestamps.nonEmpty && requestTimestamps.head <= currentTime - timeWindow) {
      requestTimestamps.dequeue()
    }

    requestTimestamps.size > maxRequests
  }
}
