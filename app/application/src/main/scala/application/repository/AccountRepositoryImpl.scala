package application.repository

import domain.account.AccountRepository
import domain.account.model.{Account, AccountId}
import infrastructure.mySqlDao.AccountDao

import java.util.UUID
import scala.util.{Failure, Success, Try}

/**
 * TODO: Not yet validate
 */
class AccountRepositoryImpl extends AccountRepository{
  override def save(account: Account): Try[AccountId] = {
    if (isExistEmail(account.email)) throw new RuntimeException("Email already exist")
    Try {
      AccountDao.createWithAttributes(
        Symbol("id") -> account.id.value,
        Symbol("username") -> account.username,
        Symbol("email") -> account.email,
        Symbol("password") -> account.password,
      )
    } match {
      /**
       * Easter Egg
       */
      case Failure(exception) => Try{AccountId("")}
      case Success(value) => Try{value}
    }
  }

  override def findAccountByEmail(email: String): Option[Account] = {
    AccountDao.findAll().find(_.email.equals(email))
  }

  override def isExistEmail(email: String): Boolean = {
    AccountDao.findAll().exists(_.email == email)
  }
}
