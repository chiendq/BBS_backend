package application.controllers.payload

import play.api.libs.json.{Json, OFormat}

case class LoginPayload(email: String, password: String)

object LoginPayload{
  implicit lazy val format: OFormat[LoginPayload] = Json.format[LoginPayload]

}