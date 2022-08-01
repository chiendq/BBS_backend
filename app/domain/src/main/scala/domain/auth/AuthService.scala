package domain.auth

import domain.account.dtos.LoginRequestDTO

trait AuthService {
  def generateJwtToken(loginRequestDTO: LoginRequestDTO): String

  def validateLoginRequest(loginRequestDTO: LoginRequestDTO): Boolean

}
