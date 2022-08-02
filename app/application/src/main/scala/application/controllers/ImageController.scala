package application.controllers

import play.api.Logger
import play.api.mvc._
import domain.post.PostConstants._

import java.io.File
import javax.inject.{Inject, Singleton}

@Singleton
class ImageController @Inject()(
                                controllerComponents: ControllerComponents)
  extends AbstractController(controllerComponents) {

  lazy val logger: Logger = Logger(getClass)

  def getImageByThumbnailId(thumbnailId: String): Action[AnyContent] = Action {
    implicit request: Request[AnyContent] =>

    val file = new File(s"$THUMBNAIL_PATH$thumbnailId.png")
    Ok.sendFile(fileName = _ => Some("image"), content = file)(defaultExecutionContext, fileMimeTypes)
      .as(IMAGE_PNG)
  }

}