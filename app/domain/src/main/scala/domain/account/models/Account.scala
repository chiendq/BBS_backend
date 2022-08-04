package domain.account.models

import domain.account.valueObjects.{Email, HashedPassword, Username}
import domain.common.valueObjects.UniqueId

case class Account(id: UniqueId, username: Username, email: Email, password: HashedPassword) {

}
