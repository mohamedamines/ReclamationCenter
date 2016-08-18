package management.resources

import akka.http.scaladsl.server.Route
import management.model.{Reclamation, ReclamationUpdate}
import management.routing.MyResource
import management.services.ReclamationService


trait ReclamationResource extends MyResource {

  val reclamationService: ReclamationService

  def reclamationRoutes: Route = pathPrefix("reclamations") {
    pathEnd {
      post {
        entity(as[Reclamation]) { reclamation =>
          completeWithLocationHeader(
            resourceId = reclamationService.createReclamation(reclamation),
            ifDefinedStatus = 201, ifEmptyStatus = 409)
          }
        }
    } ~
    path(Segment) { id =>
      get {
        complete(reclamationService.getReclamation(id))
      } ~
      put {
        entity(as[ReclamationUpdate]) { update =>
          complete(reclamationService.updateReclamation(id, update))
        }
      } ~
      delete {
        complete(reclamationService.deleteReclamation(id))
      }
    }

  }
}

