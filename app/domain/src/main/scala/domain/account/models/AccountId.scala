package domain.account.models

import domain.common.Identifier

case class AccountId(value: String) extends Identifier[String]
