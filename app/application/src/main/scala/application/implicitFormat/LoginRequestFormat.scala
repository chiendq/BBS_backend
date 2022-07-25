package application.implicitFormat

import application.payload.LoginRequest
import play.api.libs.json.{Json, OFormat}

object LoginRequestFormat{
  implicit lazy val format: OFormat[LoginRequest] = Json.format[LoginRequest]
}