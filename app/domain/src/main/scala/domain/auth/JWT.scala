package domain.auth

import domain.account.model.AccountId

import scala.util.Try

trait JWT {
  def generate(accountId: AccountId): String

  def validate(token: String): Try[AccountId]
}
