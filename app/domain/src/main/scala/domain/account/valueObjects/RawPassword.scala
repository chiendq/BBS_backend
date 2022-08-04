package domain.account.valueObjects

import domain.exceptions.account.AuthenticationFailedException

case class RawPassword(value: String) {
  if (!(value.matches(raw".*\W.*") &&
    value.matches(raw".*[a-zA-Z].*") &&
    value.matches(raw".*[0-9].*"))) {
    throw AuthenticationFailedException("Incorrect username or password")
  }
}
