package application.repositories

import domain.account.AccountConstants.{EMAIL, PASSWORD, USERNAME}
import domain.account.AccountRepository
import domain.account.models.{Account, AccountId}
import domain.common.CommonConstants.ID
import domain.common.valueObjects.Email
import infrastructure.mySqlDao.AccountDao._
import scalikejdbc.{scalikejdbcSQLInterpolationImplicitDef, sqls}

import scala.util.Try

class AccountRepositoryImpl extends AccountRepository{
  override def save(account: Account): Try[AccountId] = {
    Try {
      createWithAttributes(
        Symbol(ID)        -> account.id.value,
        Symbol(USERNAME)  -> account.username.value,
        Symbol(EMAIL)     -> account.email.value,
        Symbol(PASSWORD)  -> account.password.value,
      )
    }
  }

  override def findAccountByEmail(email: Email): Option[Account] = {
    findBy(sqls"email= ${email.value}")
  }

  override def isDuplicateEmail(email: Email): Boolean = {
    findAccountByEmail(email).fold(false)(_=>true)
  }
}
