package application.implicitFormat

import application.payload.LoginPayload
import play.api.libs.json.{Json, OFormat}

object LoginRequestFormat{
  implicit lazy val format: OFormat[LoginPayload] = Json.format[LoginPayload]
}