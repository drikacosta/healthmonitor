package br.com.healthmonitor.security

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import java.nio.charset.StandardCharsets

class JwtTokenUtilTest {

    private val secret = "uma_chave_super_secreta_32bytes"
    private val jwtTokenUtil = JwtTokenUtil(secret)

    @Test
    fun `deve gerar um token JWT válido`() {
        val token = jwtTokenUtil.generateToken("testeuser")

        assertNotNull(token)
        assertTrue(token.isNotEmpty())
    }

    @Test
    fun `deve validar um token JWT corretamente`() {
        val token = jwtTokenUtil.generateToken("testeuser")

        assertTrue(jwtTokenUtil.validateToken(token))
    }

    @Test
    fun `deve extrair corretamente o username do token`() {
        val token = jwtTokenUtil.generateToken("testeuser")
        val username = jwtTokenUtil.getUsernameFromToken(token)

        assertEquals("testeuser", username)
    }
}