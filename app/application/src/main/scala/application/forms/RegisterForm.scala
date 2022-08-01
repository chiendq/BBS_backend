package application.forms

import domain.account.dtos.RegisterPayload
import play.api.data.Form
import play.api.data.Forms.{email, mapping, nonEmptyText, text}

object RegisterForm {
  val registerForm = Form(
    mapping(
      "email" -> email,
      "username" -> text(maxLength = 50, minLength = 1),
      "password" -> text(maxLength = 20, minLength = 8)
    )(RegisterPayload.apply)(RegisterPayload.unapply)
  )
}
