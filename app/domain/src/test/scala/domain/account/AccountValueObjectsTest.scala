package domain.account

import domain.account.valueObjects.{Email, Username}
import domain.common.Paged
import domain.common.valueObjects.UniqueId
import domain.post.valueObjects.Title
import org.specs2.mutable.Specification

class AccountValueObjectsTest extends Specification {

  def mkString(n: Int) = "a"*n

  "UniqueId" should {
    "apply failure" in {
      UniqueId(mkString(50)) must throwAn[IllegalArgumentException]
      UniqueId(mkString(11)) must throwAn[IllegalArgumentException]
    }
    "apply success" in {
      UniqueId(mkString(36)) must_!= throwAn[IllegalArgumentException]("Invalid Id length")
    }
  }

  "Username" should {
    "apply empty failure" in {
      Username(mkString(0)) must throwAn[IllegalArgumentException]("Username can not left blank")
    }
    "apply over-size failure" in {
      Username(mkString(60)) must throwAn[IllegalArgumentException]("Username can not over 50 characters")
    }
    "apply success" in {
      Username(mkString(20)) must_!= throwAn[Throwable]
    }
  }

  "Email" should {
    "apply invalid emails" in {

      // [@ is not present]
      Email("mysite.ourearth.com")  must throwAn[IllegalArgumentException]("Email is not valid")
      // tld (Top Level domain) can not start with dot "."
      Email("mysite@.com.my") must throwAn[IllegalArgumentException]("Email is not valid")
      // No character before @
      Email("@you.me.net") must throwAn[IllegalArgumentException]("Email is not valid")
      // ".b" is not a valid tld
      Email("mysite123@gmail.b") must throwAn[IllegalArgumentException]("Email is not valid")
      // tld can not start with dot "."
      Email("mysite@.org.org") must throwAn[IllegalArgumentException]("Email is not valid")
      // an email should not be start with "."
      Email(".mysite@mysite.org") must throwAn[IllegalArgumentException]("Email is not valid")
      // here the regular expression only allows character, digit, underscore, and dash
      Email("mysite()*@gmail.com") must throwAn[IllegalArgumentException]("Email is not valid")
      // double dots are not allowed
      Email("mysite..1234@yahoo.com") must throwAn[IllegalArgumentException]("Email is not valid")
    }
    "apply valid email" in {
      Email("thienthangaycanh@gmail.com") must_!= throwAn[Throwable]
      Email("chiendao.com@gmail.com")     must_!= throwAn[Throwable]
      Email("chiendao.com@hanu.edu.vn")   must_!= throwAn[Throwable]
    }
  }

  "Username" should {
    "apply empty failure" in {
      Username(mkString(0)) must throwAn[IllegalArgumentException]("Username can not left blank")
    }
    "apply success" in {
      Username(mkString(50)) must_!= throwAn[Throwable]
    }
  }

  "Paged" should {
    "apply success" in {
      "empty item" in {
        Paged(items = Seq(1,2,3,4), count = 0, page = 1, pageSize = 4).totalPage == 0
      }

    }
    "apply failure" in {
      "Number of items not equals page size" in {
        Paged(items = Seq(1,2,3,4,5), count = 0, page = 1, pageSize = 4) must throwAn[IllegalArgumentException]("Number of items not equals page size")
      }
    }
  }

  "Title" should {
    "apply empty failure" in {
      Title(mkString(0)) must throwAn[IllegalArgumentException]("Title can not left blank")
    }
    "apply over-size failure" in {
      Title(mkString(151)) must throwAn[IllegalArgumentException]("Title can not over 150 characters")
    }
    "apply success" in {
      Title(mkString(20)) must_!= throwAn[Throwable]
    }
  }
}
