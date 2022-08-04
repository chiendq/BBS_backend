package domain.auth

import domain.common.valueObjects.UniqueId

import scala.util.Try

trait JWT {
  def generate(accountId: UniqueId): String

  def validate(token: String): Try[UniqueId]
}
