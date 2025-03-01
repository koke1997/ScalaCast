package utils

import com.typesafe.config.{Config, ConfigValueFactory}
import javax.inject._
import scala.concurrent.{ExecutionContext, Future}
import play.api.Configuration

@Singleton
class ConfigUtil @Inject()(config: Configuration)(implicit ec: ExecutionContext) {

  def getConfig(key: String): Future[Option[String]] = Future {
    config.getOptional[String](key)
  }

  def updateConfig(key: String, value: String): Future[Config] = Future {
    // Use ConfigValueFactory to create a ConfigValue from String
    config.underlying.withValue(key, ConfigValueFactory.fromAnyRef(value))
  }
}
