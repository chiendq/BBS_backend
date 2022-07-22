package domain.account.model

import domain.common.Identifier

case class AccountId(value: String) extends Identifier[String]
