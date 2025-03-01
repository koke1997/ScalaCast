package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import java.util.UUID
import scala.concurrent.{ExecutionContext, Future}
import java.nio.file.{Paths, Files}

@Singleton
class UploadController @Inject()(cc: ControllerComponents)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def uploadImage = Action(parse.multipartFormData) { request =>
    request.body.file("image").map { picture =>
      val filename = UUID.randomUUID().toString + "-" + picture.filename
      val uploadDir = Paths.get("public", "uploads")
      
      // Create directory if it doesn't exist
      if (!Files.exists(uploadDir)) {
        Files.createDirectories(uploadDir)
      }
      
      // Save file
      val destination = uploadDir.resolve(filename)
      picture.ref.copyTo(destination, replace = true)
      
      Ok(Json.obj(
        "success" -> true,
        "imageId" -> filename,
        "url" -> routes.Assets.versioned(s"uploads/$filename").url
      ))
    }.getOrElse {
      BadRequest(Json.obj("error" -> "Missing image file"))
    }
  }
}