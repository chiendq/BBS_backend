package application.forms

import domain.account.dtos.LoginPayLoad
import play.api.data.Form
import play.api.data.Forms.{email, mapping, text}

object LoginForm {
  val loginForm = Form(
    mapping(
      "email" -> email,
      "password" -> text(minLength = 8)
    )(LoginPayLoad.apply)(LoginPayLoad.unapply)
  )
}
