package application.json

import domain.account.dtos.{LoginRequestDTO, LoginResponseDTO}
import domain.common.valueObjects.{Email, RawPassword}
import play.api.libs.json.{JsResult, JsValue, Json, Reads, Writes}

object AccountFormat {
  implicit lazy val loginResponseDTOWrites =new  Writes[LoginResponseDTO] {
    override def writes(o: LoginResponseDTO): JsValue =
      Json.obj(
        "accountId" -> o.accountId.value,
        "username" -> o.username.value
    )
  }

  implicit lazy val loginRequestDTOWrites = new Reads[LoginRequestDTO] {

    override def reads(json: JsValue): JsResult[LoginRequestDTO] =
      for {
        email <- (json \ "email").validate[String]
        password <- (json \ "password").validate[String]
      } yield LoginRequestDTO(Email(email), RawPassword(password))
  }


}