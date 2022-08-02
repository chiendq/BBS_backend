package domain.account.dtos

import domain.common.valueObjects.{Email, RawPassword}

case class LoginRequestDTO(email: Email, password: RawPassword)
