package application.payload

import play.api.data.Form
import play.api.data.Forms.{email, mapping, nonEmptyText, number, text}


case class RegisterPayload(email: String, username: String, password: String){
//  require(email.length > 0, "Email is wrong")
}

object RegisterForm{
  val registerForm = Form(
    mapping(
      "email" -> email,
      "username"  -> nonEmptyText(maxLength = 50),
      "password" -> nonEmptyText(minLength = 10, maxLength = 20)
    )(RegisterPayload.apply)(RegisterPayload.unapply)
  )
}