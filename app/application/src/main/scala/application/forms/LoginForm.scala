package application.forms

import domain.account.dtos.LoginRequestDTO
import play.api.data.Form
import play.api.data.Forms.{email, mapping, text}

object LoginForm {
  val loginForm = Form(
    mapping(
      "email" -> email,
      "password" -> text(minLength = 8)
    )(LoginRequestDTO.apply)(LoginRequestDTO.unapply)
  )
}
