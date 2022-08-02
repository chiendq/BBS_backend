package application.repository

import domain.account.AccountConstants.{EMAIL, PASSWORD, USERNAME}
import domain.account.AccountRepository
import domain.account.models.{Account, AccountId}
import domain.common.CommonConstants.ID
import domain.common.valueObjects.Email
import infrastructure.mySqlDao.AccountDao

import scala.util.Try

class AccountRepositoryImpl extends AccountRepository{
  override def save(account: Account): Try[AccountId] = {
    Try {
      AccountDao.createWithAttributes(
        Symbol(ID)        -> account.id.value,
        Symbol(USERNAME)  -> account.username.value,
        Symbol(EMAIL)     -> account.email.value,
        Symbol(PASSWORD)  -> account.password.value,
      )
    }
  }

  override def findAccountByEmail(email: String): Option[Account] = {
    AccountDao.findAll().find(_.email.value == email)
  }

  override def isExistEmail(email: Email): Boolean = {
    AccountDao.findAll().exists(_.email.value == email)
  }
}
