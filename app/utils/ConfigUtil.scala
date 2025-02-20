package utils

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}
import play.api.Configuration

@Singleton
class ConfigUtil @Inject()(config: Configuration)(implicit ec: ExecutionContext) {

  def getConfig(key: String): Future[Option[String]] = Future {
    config.getOptional[String](key)
  }

  def updateConfig(key: String, value: String): Future[Unit] = Future {
    // Assuming we have a mutable configuration for the sake of this example
    // In a real-world scenario, you might need to update a database or a file
    config.underlying.withValue(key, value)
  }
}
