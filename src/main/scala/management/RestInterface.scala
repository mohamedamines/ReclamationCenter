package management

import scala.concurrent.ExecutionContext
import akka.http.scaladsl.server.Route
import management.resources.ReclamationResource
import management.services.ReclamationService

trait RestInterface extends Resources {

  implicit def executionContext: ExecutionContext

  lazy val reclamationService = new ReclamationService

  val routes: Route = reclamationRoutes

}

trait Resources extends ReclamationResource
