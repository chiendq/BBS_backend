package implicitFormat

import play.api.libs.json.{JsValue, Json, Writes}
import post.Post
import implicitFormat.AccountFormat._
object PostFormat {
  import converter.DateTimeConverter._
  implicit lazy val postWriter: Writes[Post] = (post: Post) => Json.obj(
    "id" -> post.id.value,
    "title" -> post.title,
    "content" -> post.content,
    "authorName" -> post.authorName,
    "createdAt" -> toYyyyMmDdString(post.createdAt),
    "updatedOn" -> toYyyyMmDdString(post.updatedOn),
    "thumbnail" -> post.thumbnail,
    "account" -> Json.toJson(post.account))
}
