package application.jwt

import application.jwt.SecurityConstants.{EXPIRATION_TIME, SECRET, TOKEN_PREFIX}
import domain.auth.JWT
import domain.common.valueObjects.UniqueId
import domain.exceptions.account.AuthenticationFailedException
import io.jsonwebtoken.{Jwts, SignatureAlgorithm}

import java.util.Date
import scala.util.{Failure, Success, Try}

object JWTImpl extends JWT {
  override def generate(accountId: UniqueId): String = {
    Jwts.builder()
      .setSubject(accountId.value)
      .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
      .signWith(SignatureAlgorithm.HS512, SECRET)
      .compact()
  }

  override def validate(token: String): Try[UniqueId] = Try {
    Try {
      Jwts.parser()
        .setSigningKey(SECRET)
        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
        .getBody
        .getSubject
    } match {
      case Failure(_) => throw AuthenticationFailedException("Authentication failed!")
      case Success(value) => UniqueId(value)
    }
  }
}
