package application.services

import domain.account.{AccountRepository, AccountService}
import domain.account.model.{Account, AccountId}

import java.util.UUID
import javax.inject.{Inject, Singleton}
import scala.util.Try

@Singleton
class AccountServiceImpl @Inject()(authService: AuthService,
                                    accountRepository: AccountRepository)
  extends AccountService{

  override def save(email: String, username: String, password: String): Try[AccountId] = {
    Try{
      val uuid = UUID.randomUUID().toString
      val hashedPassword = authService.hashPassword(password)
      val account = Account(AccountId(uuid), username, email, hashedPassword)

      accountRepository.save(account).get
    }
  }

  override def findByEmail(email: String): Option[Account] = {
    accountRepository.findAccountByEmail(email)
  }
}
