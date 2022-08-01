package domain.auth

import domain.account.dtos.LoginRequestDTO

import scala.util.Try

trait AuthService {
  def validateJwt(token: String): Try[Unit]

  def generateJwtToken(loginRequestDTO: LoginRequestDTO): String

  def validateLoginRequest(loginRequestDTO: LoginRequestDTO): Boolean

  def hashPassword(password: String): String

}
