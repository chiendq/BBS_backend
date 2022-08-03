package application.jwt

import application.jwt.SecurityConstants.{EXPIRATION_TIME, SECRET, TOKEN_PREFIX}
import domain.account.models.AccountId
import domain.auth.JWT
import domain.exception.AuthenticationFailedException
import io.jsonwebtoken.{Jwts, SignatureAlgorithm}

import java.util.Date
import scala.util.{Failure, Success, Try}

object JWTImpl extends JWT {
  override def generate(accountId: AccountId): String = {
    Jwts.builder()
      .setSubject(accountId.value)
      .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
      .signWith(SignatureAlgorithm.HS512, SECRET)
      .compact()
  }

  override def validate(token: String): Try[AccountId] = Try {
    Try {
      Jwts.parser()
        .setSigningKey(SECRET)
        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
        .getBody
        .getSubject
    } match {
      case Failure(exception) => throw AuthenticationFailedException("Authentication failed!")
      case Success(value) => AccountId(value)
    }
  }
}
