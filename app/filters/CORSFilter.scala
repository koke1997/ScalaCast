package filters

import javax.inject._
import play.api.mvc._
import scala.concurrent.{Future, ExecutionContext}
import akka.stream.Materializer

@Singleton
class CORSFilter @Inject()(implicit val mat: Materializer, ec: ExecutionContext) extends Filter {
  def apply(nextFilter: RequestHeader => Future[Result])
           (requestHeader: RequestHeader): Future[Result] = {
    nextFilter(requestHeader).map { result =>
      result.withHeaders(
        "Access-Control-Allow-Origin" -> "*",
        "Access-Control-Allow-Methods" -> "GET, POST, OPTIONS, PUT, DELETE",
        "Access-Control-Allow-Headers" -> "Accept, Content-Type, Origin, X-Requested-With"
      )
    }
  }
}
