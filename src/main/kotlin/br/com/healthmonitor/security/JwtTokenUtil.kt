package br.com.healthmonitor.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component

class JwtTokenUtil {
    private val expirationMs: Long = 86400000 // 1 dia
    private val secretKey: SecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256)

    fun generateToken(username: String): String {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expirationMs))
            .signWith(secretKey)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        return try {
            val claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token)
            !claims.body.expiration.before(Date())
        } catch (e: Exception) {
            false
        }
    }

    fun extractUsername(token: String): String {
        val claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token)
        return claims.body.subject
    }
}
