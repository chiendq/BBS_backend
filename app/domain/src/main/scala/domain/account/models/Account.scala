package domain.account.models

import domain.common.valueObjects.{Email, HashedPassword, Username}

case class Account(id: AccountId, username: Username, email: Email, password: HashedPassword) {

}
