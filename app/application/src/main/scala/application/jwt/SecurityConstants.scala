package application.jwt

object SecurityConstants {
  val SECRET = "SecretToSignJWT"
  val EXPIRATION_TIME = 1800000 // 30 minutes
  val TOKEN_PREFIX = "Bearer "
  val TOKEN_NAME="BBS"
}
