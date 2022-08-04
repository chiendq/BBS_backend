package domain.account.models

import domain.common.valueObjects.{Email, HashedPassword, UniqueId, Username}

case class Account(id: UniqueId, username: Username, email: Email, password: HashedPassword) {

}
