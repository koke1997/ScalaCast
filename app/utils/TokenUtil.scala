package utils

import java.util.Date
import pdi.jwt.{Jwt, JwtAlgorithm, JwtClaim}

object TokenUtil {
  private val secretKey = "your-secret-key"

  def generateToken(username: String): String = {
    val claim = JwtClaim(
      expiration = Some(new Date().getTime / 1000 + 3600),
      issuedAt = Some(new Date().getTime / 1000),
      subject = Some(username)
    )
    Jwt.encode(claim, secretKey, JwtAlgorithm.HS256)
  }

  def validateToken(token: String): Boolean = {
    Jwt.decode(token, secretKey, Seq(JwtAlgorithm.HS256)).isSuccess
  }
}
