package domain.account.valueObjects

case class Email(value: String) {
  if (!value.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
    throw new IllegalArgumentException("Email is not valid")
  }
}
