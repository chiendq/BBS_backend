package application.services

import application.jwt.SecurityConstants._
import application.payload.LoginRequest
import io.jsonwebtoken.{Claims, Jwts, SignatureAlgorithm}
import play.api.Configuration
import skinny.logging.Logger
import java.util.Date
import javax.inject.Inject
import scala.util.Try
import com.github.t3hnar.bcrypt._
class AuthService @Inject()(config: Configuration) {
  val logger: Logger = Logger(getClass)

  def validateJwt(token: String): Try[Claims] = {
    Try {
      Jwts.parser()
        .setSigningKey(SECRET)
        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
        .getBody
    }
  }

  def generateJwtToken(loginRequest: LoginRequest): String = {
    Jwts.builder()
      .setSubject(loginRequest.username)
      .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
      .signWith(SignatureAlgorithm.HS512, SECRET)
      .compact()
  }

  def hashPassword(password: String): String = {
    val salt = generateSalt
    password.bcryptBounded(salt)
  }

  def validatePassword(plainPassword: String, hashedPassword: String): Boolean = {
    plainPassword.isBcryptedBounded(hashedPassword)
  }
}
