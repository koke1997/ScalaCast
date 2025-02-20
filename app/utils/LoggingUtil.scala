package utils

object LoggingUtil {

  def logInfo(message: String): Unit = {
    println(s"INFO: $message")
  }

  def logWarning(message: String): Unit = {
    println(s"WARNING: $message")
  }

  def logError(message: String): Unit = {
    println(s"ERROR: $message")
  }
}
