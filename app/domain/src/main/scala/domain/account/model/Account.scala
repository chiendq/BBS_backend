package domain.account.model


case class Account(id: AccountId, username: String, email: String, password: String) {
  def isLogined(email: String, pass: String) = email == this.email && pass == this.password
}
