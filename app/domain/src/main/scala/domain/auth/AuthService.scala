package domain.auth

import domain.account.dtos.LoginRequestDTO
import domain.common.valueObjects.Email

trait AuthService {
  def generateJwtToken(email: Email): String

  def validateLoginRequest(loginRequestDTO: LoginRequestDTO): Boolean

}
