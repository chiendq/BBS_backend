package application.controllers

import domain.post.PostService
import play.api.Logger
import play.api.libs.ws.WSClient
import play.api.mvc._

import java.io.File
import javax.inject.{Inject, Singleton}

@Singleton
class ImageController @Inject()(ws: WSClient,
                                postService: PostService,
                                controllerComponents: ControllerComponents)
  extends AbstractController(controllerComponents) {

  lazy val logger: Logger = Logger(getClass)

  def getImageByThumbnailId(thumbnailId: String): Action[AnyContent] = Action {
    implicit request: Request[AnyContent] =>

    val file = new File(s"public/images/thumbnails/$thumbnailId.png")
    Ok.sendFile(fileName = _ => Some("image"), content = file)(defaultExecutionContext, fileMimeTypes)
      .as("image/png")
  }

}