package domain.account.models

import domain.common.Identifier

case class AccountId(value: String) extends Identifier[String]{
  if(value.length != 36) throw new IllegalArgumentException("Account_id incorrect type")
}
