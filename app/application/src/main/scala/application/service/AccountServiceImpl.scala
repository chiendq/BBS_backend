package application.service

import domain.account.AccountService
import domain.account.model.AccountId
import infrastructure.mySqlDao.AccountDao

// VALIDATE HERE
class AccountServiceImpl extends AccountService{
  def login(email: String, password: String) = ???

  override def isExistAccountId(value: String): Boolean = AccountDao.findById(AccountId(value)) match {
    case Some(value) => true
    case None => false
  }
}
