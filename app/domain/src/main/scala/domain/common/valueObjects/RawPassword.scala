package domain.common.valueObjects

import domain.exceptions.account.AuthenticationFailedException


case class RawPassword(value: String){
  if(!(value.matches(raw".*\W.*") &&
    value.matches(raw".*[a-zA-Z].*") &&
    value.matches(raw".*[0-9].*"))){
    throw AuthenticationFailedException("Authentication failed!")
  }
}
