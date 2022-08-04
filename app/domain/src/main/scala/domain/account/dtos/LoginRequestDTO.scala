package domain.account.dtos

import domain.account.valueObjects.{Email, RawPassword}

case class LoginRequestDTO(email: Email, password: RawPassword)
