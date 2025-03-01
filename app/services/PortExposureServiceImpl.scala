package services

import javax.inject._
import scala.concurrent.{Future, ExecutionContext}
import play.api.libs.json._

@Singleton
class PortExposureServiceImpl @Inject()(implicit ec: ExecutionContext) {
  
  def exposePort(port: Int): Future[Unit] = Future.successful(())
  
  def closePort(port: Int): Future[Unit] = Future.successful(())
  
  def exposePortsForEndUsers(ports: List[Int]): Future[Unit] = Future.successful(())
  
  def exposePortsForManualOperators(ports: List[Int]): Future[Unit] = Future.successful(())
  
  def closeAllPorts(): Future[Unit] = Future.successful(())
  
  def batch(operations: List[JsObject]): Future[Unit] = Future.successful(())
}