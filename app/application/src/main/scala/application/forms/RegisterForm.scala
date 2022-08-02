package application.forms

import domain.account.dtos.RegisterPayload
import play.api.data.Form
import play.api.data.Forms.{mapping, text}

object RegisterForm {
  val registerForm = Form(
    mapping(
      "email" -> text.verifying("Email not correct", _.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")),
      "username" -> text(maxLength = 50, minLength = 1),
      "password" -> text(maxLength = 20, minLength = 8)
    )(RegisterPayload.apply)(RegisterPayload.unapply)
  )
}
