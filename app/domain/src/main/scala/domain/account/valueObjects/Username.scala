package domain.account.valueObjects

case class Username(value: String) {
  if (value.length > 50) throw new IllegalArgumentException("Username can not over 50 characters")
  if (value.isEmpty) throw new IllegalArgumentException("Username can not left blank")
}
