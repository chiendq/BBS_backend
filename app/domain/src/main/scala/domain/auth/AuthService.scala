package domain.auth

import domain.account.dtos.LoginRequestDTO
import domain.account.valueObjects.Email

trait AuthService {
  def generateJwtToken(email: Email): String

  def validateLoginRequest(loginRequestDTO: LoginRequestDTO): Boolean

}
