package domain.auth

import domain.account.models.AccountId

import scala.util.Try

trait JWT {
  def generate(accountId: AccountId): String

  def validate(token: String): Try[AccountId]
}
