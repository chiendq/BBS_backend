package implicitFormat

import account.LoginDto
import play.api.libs.json.Json

object LoginDtoFormat {
  implicit lazy val loginDtoFormat = Json.format[LoginDto]
}
