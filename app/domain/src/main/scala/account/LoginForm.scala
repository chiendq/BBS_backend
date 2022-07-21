package account

import play.api.data.Form
import play.api.data.Forms._

case class LoginDto private(email: String, password: String)

object LoginDto {
  val form: Form[LoginDto] = Form(
    mapping(
      "email" -> email,
      "password" -> nonEmptyText
    )(LoginDto.apply)(LoginDto.unapply)
  )
}

