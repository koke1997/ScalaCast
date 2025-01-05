import scala.concurrent.{Future, ExecutionContext}
import scala.sys.process._
import scala.util.{Success, Failure}

object Monitoring {
  implicit val ec: ExecutionContext = ExecutionContext.global

  def monitorCPU(): Future[Unit] = Future {
    val cpuUsage = "top -b -n1 | grep 'Cpu(s)' | awk '{print $2 + $4}'".!!
    println(s"CPU Usage: $cpuUsage")
  }

  def monitorMemory(): Future[Unit] = Future {
    val memoryUsage = "free -m | awk 'NR==2{printf \"Memory Usage: %s/%sMB (%.2f%%)\\n\", $3,$2,$3*100/$2 }'".!!
    println(s"Memory Usage: $memoryUsage")
  }

  def monitorLogs(): Future[Unit] = Future {
    val logErrors = "tail -f log/error.log | grep --line-buffered 'ERROR'".!!
    println(s"Log Errors: $logErrors")
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
}
