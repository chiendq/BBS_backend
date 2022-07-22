package application.implicitFormat

import domain.account.model.Account
import play.api.libs.json.{Json, OFormat, Writes}

object AccountFormat {
  implicit lazy val accountFormat: Writes[Account] = (account: Account) => Json.obj(
    "id" -> account.id.value,
    "email" -> account.email,
    "username" -> account.username,
    "password" -> account.password)

}
