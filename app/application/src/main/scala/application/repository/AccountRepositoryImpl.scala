package application.repository

import domain.account.AccountRepository
import domain.account.model.{Account, AccountId}
import infrastructure.mySqlDao.AccountDao

import java.util.UUID
import scala.util.Try

/**
 * TODO: Not yet validate
 */
class AccountRepositoryImpl extends AccountRepository{
  override def save(account: Account): Try[AccountId] = {
    Try{
      AccountDao.createWithAttributes(
        Symbol("id") -> account.id.value,
        Symbol("email") -> account.email,
        Symbol("username") -> account.username,
        Symbol("password") -> account.password,
      )
    }
  }

  override def findAccountByEmail(email: String): Option[Account] = {
    AccountDao.findAll().find(_.email.equals(email))
  }
}
