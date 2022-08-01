package application.services

import application.jwt.SecurityConstants._
import com.auth0.jwt.exceptions.JWTVerificationException
import io.jsonwebtoken.{Claims, Jwts, SignatureAlgorithm}
import play.api.Configuration
import skinny.logging.Logger

import java.util.Date
import javax.inject.{Inject, Singleton}
import scala.util.Try
import com.github.t3hnar.bcrypt._
import domain.account.AccountRepository
import domain.account.dtos.LoginRequestDTO
import play.api.mvc.Request
@Singleton
class AuthService @Inject()(config: Configuration,
                            accountRepo : AccountRepository) {
  val logger: Logger = Logger(getClass)

  def validateJwt(token: String): Try[Claims] = {
    Try {
      Jwts.parser()
        .setSigningKey(SECRET)
        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
        .getBody
    }
  }

  def generateJwtToken(loginRequestDTO: LoginRequestDTO): String = {
    val accountId = accountRepo.findAccountByEmail(loginRequestDTO.email).get.id
    Jwts.builder()
      .setSubject(accountId.value)
      .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
      .signWith(SignatureAlgorithm.HS512, SECRET)
      .compact()
  }

  def validateLoginRequest(loginRequestDTO: LoginRequestDTO): Boolean ={
    accountRepo.findAccountByEmail(loginRequestDTO.email) match {
      case Some(account) => {
        val result = validatePassword(loginRequestDTO.password, account.password)
        result
      }
      case _ => false
    }
  }

  def hashPassword(password: String): String = {
    val salt = generateSalt
    password.bcryptBounded(salt)
  }

  def extractBearerToken[A](request: Request[A]): Option[String] = {
    val cookies = request.cookies
    cookies.get("Bearer").map(_.value).orElse(throw new JWTVerificationException("No token found!"))
  }

  private def validatePassword(plainPassword: String, hashedPassword: String): Boolean = {
    plainPassword.isBcryptedBounded(hashedPassword)
  }

  def extractSubject[A](request: Request[A]): String = {
    val token = extractBearerToken(request).get
    val claims = validateJwt(token).get
    claims.getSubject
  }

}
