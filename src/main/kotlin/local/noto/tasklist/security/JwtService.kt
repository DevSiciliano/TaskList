package local.noto.tasklist.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Base64
import java.util.Date
import javax.crypto.SecretKey

@Component
class JwtService(
    @Value("\${jwt.secret}") secret: String,
    @Value("\${jwt.expiration}") private val expirationMs: Long
) {

    private val key: SecretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret))

    fun generateToken(subject: String): String =
        Jwts.builder()
            .subject(subject)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + expirationMs))
            .signWith(key)
            .compact()

    fun extractSubject(token: String): String =
        Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .payload
            .subject

    fun isTokenValid(token: String): Boolean =
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token)
            true
        } catch (ex: Exception) {
            false
        }
}
