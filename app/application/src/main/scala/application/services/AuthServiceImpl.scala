package application.services

import domain.auth.{AuthService, JWT, PasswordHash}

import javax.inject.{Inject, Singleton}
import domain.account.AccountRepository
import domain.account.dtos.LoginRequestDTO
import domain.account.valueObjects.{Email, HashedPassword}
import scala.util.Random
import scala.concurrent.{ExecutionContext, Future}
@Singleton
class AuthServiceImpl @Inject()(
                                 accountRepo : AccountRepository,
                                 jwt: JWT,
                                 passwordHash: PasswordHash
                               ) extends AuthService {
  override def generateJwtToken(email: Email): String = {
    val accountId = accountRepo.findAccountByEmail(email).get.id
    jwt.generate(accountId)
  }

  override def validateLoginRequest(loginRequestDTO: LoginRequestDTO): Boolean ={
    accountRepo.findAccountByEmail(loginRequestDTO.email) match {
      case Some(account) => passwordHash.verify(loginRequestDTO.password, account.password)
      case _ => false
    }
  }

}
