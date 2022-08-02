package domain.account.dtos

import domain.common.valueObjects.{Email, Password}

case class LoginRequestDTO(email: Email, password: Password)
