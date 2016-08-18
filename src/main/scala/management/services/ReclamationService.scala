package management.services

import management.model.{Reclamation, ReclamationUpdate}

import scala.concurrent.{ExecutionContext, Future}

class ReclamationService(implicit val executionContext: ExecutionContext) {

  var reclamations = List.empty[Reclamation]

  def createReclamation(reclamation: Reclamation): Future[Option[String]] = Future {
    reclamations.find(_.id == reclamation.id) match {
      case Some(reclam) => None
      case None =>
        reclamations = reclamations :+ reclamation
        Some(reclamation.id)
    }
  }

  def getReclamation(id: String): Future[Option[Reclamation]] = Future {
    reclamations.find(_.id == id)
  }

  def updateReclamation(id: String, update: ReclamationUpdate): Future[Option[Reclamation]] = {

    def updateEntity(reclamation: Reclamation): Reclamation = {
      val title = update.title.getOrElse(reclamation.title)
      val text = update.text.getOrElse(reclamation.text)
      Reclamation(id, title, text)
    }

    getReclamation(id).flatMap {
      case None => Future { None }
      case Some(reclamation) =>
        val updatedReclamation = updateEntity(reclamation)
        deleteReclamation(id).flatMap { _ =>
          createReclamation(updatedReclamation).map(_ => Some(updatedReclamation))
        }
    }
  }

  def deleteReclamation(id: String): Future[Unit] = Future {
    reclamations = reclamations.filterNot(_.id == id)
  }
}
