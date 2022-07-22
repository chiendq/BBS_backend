package application.implicitFormat

import domain.post.model.Post
import play.api.libs.json.{Json, Writes}

object PostFormat {

  import application.converter.DateTimeConverter._
  import application.implicitFormat.AccountFormat._

  implicit lazy val postWriters: Writes[Post] = (post: Post) => Json.obj(
    "id" -> post.id.value,
    "title" -> post.title,
    "content" -> post.content,
    "authorName" -> post.authorName,
    "createdAt" -> toJodaDateTimeString(post.createdAt),
    "updatedOn" -> toJodaDateTimeString(post.updatedOn),
    "thumbnail" -> post.thumbnail,
    "account" -> Json.toJson(post.account))

  implicit lazy val postDtoWrites: Writes[Post] = (post: Post) => Json.obj(
    "id" -> post.id.value,
    "title" -> post.title,
    "content" -> post.content,
    "authorName" -> post.authorName,
    "createdAt" -> toJodaDateTimeString(post.createdAt),
    "updatedOn" -> toJodaDateTimeString(post.updatedOn),
    "thumbnail" -> post.thumbnail)

  implicit lazy val seqPostDtoFormat: Writes[Seq[Post]] = (seqPost: Seq[Post]) =>
    Json.arr(
      seqPost.map(post => {
        Json.toJson(post)(postDtoWrites)
      }))

}
