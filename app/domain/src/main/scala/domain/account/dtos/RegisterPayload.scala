package domain.account.dtos

case class RegisterPayload(email: String, username: String, password: String) {
  //  require(email.length > 0, "Email is wrong")
}
