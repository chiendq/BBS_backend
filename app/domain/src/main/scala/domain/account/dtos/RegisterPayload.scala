package domain.account.dtos

import domain.account.valueObjects.{Email, RawPassword, Username}

case class RegisterPayload(email: Email, username: Username, password: RawPassword) {
}
