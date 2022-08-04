package domain.post

import domain.common.valueObjects.UniqueId
import domain.post.valueObjects.{AuthorName, Content, Title}
import org.specs2.mutable.Specification
class PostValueObjectsTest() extends Specification{
  def mkString(n: Int) = "a"*n

  "UniqueId" should {
    "apply failure" in {
      UniqueId(mkString(35)) must throwAn[IllegalArgumentException]
      UniqueId(mkString(37)) must throwAn[IllegalArgumentException]
    }
    "apply success" in {
      UniqueId(mkString(36)) must_!= throwAn[IllegalArgumentException]
    }
  }

  "Title" should {
    "apply failure" in {
      Title(mkString(0)) must throwAn[IllegalArgumentException]
      Title(mkString(151)) must throwAn[IllegalArgumentException]
    }
    "apply success" in {
      Title(mkString(1)) must_!= throwAn[IllegalArgumentException]
      Title(mkString(150)) must_!= throwAn[IllegalArgumentException]
    }
  }

  "AuthorName" should {
    "apply failure" in {
      AuthorName(mkString(0)) must throwAn[IllegalArgumentException]
      AuthorName(mkString(52)) must throwAn[IllegalArgumentException]
      AuthorName(mkString(51)) must throwAn[IllegalArgumentException]
    }
    "apply success" in {
      AuthorName(mkString(1)) must_!= throwAn[IllegalArgumentException]
      AuthorName(mkString(49)) must_!= throwAn[IllegalArgumentException]
    }
  }

  "Content" should {
    "apply failure" in {
      Content(mkString(0)) must throwAn[IllegalArgumentException]
    }
    "apply success" in {
      Content(mkString(1)) must_!= throwAn[IllegalArgumentException]
      Content(mkString(31293)) must_!= throwAn[IllegalArgumentException]
    }
  }
}
