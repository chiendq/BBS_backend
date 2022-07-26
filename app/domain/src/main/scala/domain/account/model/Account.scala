package domain.account.model

import play.api.libs.json._


case class Account(id: AccountId, username: String, email: String, password: String) {
//  def isLogined(email: String, pass: String) = email == this.email && pass == this.password
}

object AccountFormat {
  implicit lazy val accountFormat: Writes[Account] = (account: Account) => Json.obj(
    "id" -> account.id.value,
    "email" -> account.email,
    "username" -> account.username,
    "password" -> account.password)

}
