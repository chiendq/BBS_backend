package application.json

import domain.account.dtos.LoginResponseDTO
import domain.account.model.Account
import play.api.libs.json.{JsObject, JsValue, Json, Writes}

object AccountFormat {
  implicit lazy val loginResponseDTOFormat =new  Writes[LoginResponseDTO] {
    override def writes(o: LoginResponseDTO): JsValue = Json.obj(
      "accountId" -> o.accountId,
      "username" -> o.username
    )
  }
}