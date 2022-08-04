package domain.post

import domain.post.models.UUID
import org.specs2.mutable.Specification

class PostValueObjectsTest() extends Specification{
  def mkString(n: Int) = "a"*n

  "UniqueId" should {
    "apply failure" in {
      UUID(mkString(36)) must throwAn[IllegalArgumentException]
    }
    "apply success" in {
      UUID(mkString(22)).value.length == 36
    }
  }
//  title: String
//  ,
//  content: String
//  ,
//  authorName: String
//  ,
//  createdAt: DateTime
//  ,
//  updatedOn: DateTime
//  ,
//  thumbnail: String
//  ,
//  account: Option[Acc
}
