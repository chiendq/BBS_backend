package application.repository

import domain.account.AccountConstants.{EMAIL, PASSWORD, USERNAME}
import domain.account.AccountRepository
import domain.account.model.{Account, AccountId}
import domain.common.CommonConstants.ID
import infrastructure.mySqlDao.AccountDao

import scala.util.Try

class AccountRepositoryImpl extends AccountRepository{
  override def save(account: Account): Try[AccountId] = {
    Try {
      AccountDao.createWithAttributes(
        Symbol(ID)        -> account.id.value,
        Symbol(USERNAME)  -> account.username,
        Symbol(EMAIL)     -> account.email,
        Symbol(PASSWORD)  -> account.password,
      )
    }
  }

  override def findAccountByEmail(email: String): Option[Account] = {
    AccountDao.findAll().find(_.email.equals(email))
  }

  override def isExistEmail(email: String): Boolean = {
    AccountDao.findAll().exists(_.email.equals(email))
  }
}
