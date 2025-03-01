package services

import javax.inject._
import scala.concurrent.{Future, ExecutionContext}
import scala.sys.process._
import scala.util.{Success, Failure}

@Singleton
class MonitoringService @Inject()(implicit ec: ExecutionContext) {

  def monitorCPU(): Future[Unit] = Future {
    try {
      val cpuUsage = "top -b -n1 | grep 'Cpu(s)' | awk '{print $2 + $4}'".!!
      println(s"CPU Usage: $cpuUsage")
    } catch {
      case e: Exception =>
        println(s"Error monitoring CPU: ${e.getMessage}")
        retryMonitoring("CPU")
    }
  }

  def monitorMemory(): Future[Unit] = Future {
    try {
      val memoryUsage = "free -m | awk 'NR==2{printf \"Memory Usage: %s/%sMB (%.2f%%)\\n\", $3,$2,$3*100/$2 }'".!!
      println(s"Memory Usage: $memoryUsage")
    } catch {
      case e: Exception =>
        println(s"Error monitoring memory: ${e.getMessage}")
        retryMonitoring("Memory")
    }
  }

  def monitorLogs(): Future[Unit] = Future {
    try {
      val logErrors = "tail -f log/error.log | grep --line-buffered 'ERROR'".!!
      println(s"Log Errors: $logErrors")
    } catch {
      case e: Exception =>
        println(s"Error monitoring logs: ${e.getMessage}")
        retryMonitoring("Logs")
    }
  }

  def restartService(serviceName: String): Future[Unit] = Future {
    val restartCommand = s"systemctl restart $serviceName"
    val result = restartCommand.!
    if (result == 0) {
      println(s"Service $serviceName restarted successfully.")
    } else {
      println(s"Failed to restart service $serviceName.")
    }
  }

  def monitorAndRestart(serviceName: String): Future[Unit] = {
    for {
      _ <- monitorCPU()
      _ <- monitorMemory()
      _ <- monitorLogs()
      _ <- restartService(serviceName)
    } yield ()
  }

  private def retryMonitoring(monitoringType: String, retryCount: Int = 0): Future[Unit] = {
    if (retryCount < 3) {
      println(s"Retrying $monitoringType monitoring... Attempt ${retryCount + 1}")
      monitoringType match {
        case "CPU" => monitorCPU().recoverWith {
          case _ => retryMonitoring(monitoringType, retryCount + 1)
        }
        case "Memory" => monitorMemory().recoverWith {
          case _ => retryMonitoring(monitoringType, retryCount + 1)
        }
        case "Logs" => monitorLogs().recoverWith {
          case _ => retryMonitoring(monitoringType, retryCount + 1)
        }
      }
    } else {
      Future.failed(new Exception(s"Failed to monitor $monitoringType after 3 attempts"))
    }
  }
}
