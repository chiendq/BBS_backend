package application.forms

import domain.account.dtos.RegisterPayload
import play.api.data.Form
import play.api.data.Forms.{email, mapping, nonEmptyText}

object RegisterForm {
  val registerForm = Form(
    mapping(
      "email" -> email,
      "username" -> nonEmptyText(maxLength = 50),
      "password" -> nonEmptyText(minLength = 10, maxLength = 20)
    )(RegisterPayload.apply)(RegisterPayload.unapply)
  )
}
