package br.com.healthmonitor.controller

import br.com.healthmonitor.security.JwtTokenUtil
import br.com.healthmonitor.service.UserService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.*

data class RegisterRequest(val name: String, val email: String, val password: String)
data class AuthRequest(val email: String, val password: String)
data class AuthResponse(val token: String)


@RestController
@RequestMapping("/auth")
class AuthController(
    private val userService: UserService,
    private val authenticationManager: AuthenticationManager,
    private val jwtTokenUtil: JwtTokenUtil
) {


    @PostMapping("/register")
    fun register(@Valid @RequestBody request: RegisterRequest): ResponseEntity<String> {
        userService.saveUser(request.name, request.email, request.password)
        return ResponseEntity.ok("Usuário registrado com sucesso")
    }


    @PostMapping("/login")
    fun login(@RequestBody request: AuthRequest): ResponseEntity<Any> {
        return try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(request.email, request.password)
            )
            val token = jwtTokenUtil.generateToken(request.email)
            ResponseEntity.ok(AuthResponse(token))
        } catch (e: AuthenticationException) {
            ResponseEntity.status(401).body(mapOf("error" to "Email ou senha inválidos"))
        }
    }


}