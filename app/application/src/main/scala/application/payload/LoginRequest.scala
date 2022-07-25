package application.payload

import play.api.libs.json.{Json, OFormat}

case class LoginRequest(username: String, password: String)