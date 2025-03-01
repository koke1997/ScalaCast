package controllers

import javax.inject._
import play.api.mvc._
import scala.concurrent.ExecutionContext
import play.api.Environment

@Singleton
class WebRTCController @Inject()(
  env: Environment, 
  cc: ControllerComponents
)(implicit ec: ExecutionContext) extends AbstractController(cc) {
  
  def index() = Action {
    Ok(views.html.webrtc())
  }
  
  def test() = Action {
    val file = env.getFile("public/webcam-test.html")
    if (file.exists()) {
      Ok.sendFile(
        content = file,
        inline = true
      )
    } else {
      NotFound("File not found: public/webcam-test.html")
    }
  }
  
  def simpleCam() = Action {
    val file = env.getFile("public/simple-cam.html")
    if (file.exists()) {
      Ok.sendFile(
        content = file,
        inline = true
      )
    } else {
      NotFound("File not found: public/simple-cam.html")
    }
  }
  
  def fileCam() = Action {
    val file = env.getFile("public/file-cam.html")
    if (file.exists()) {
      Ok.sendFile(
        content = file,
        inline = true
      )
    } else {
      NotFound("File not found: public/file-cam.html")
    }
  }

  def androidWebRTC() = Action {
    val file = env.getFile("public/android-webrtc.html")
    if (file.exists()) {
      Ok.sendFile(
        content = file,
        inline = true
      )
    } else {
      NotFound("File not found: public/android-webrtc.html")
    }
  }
}