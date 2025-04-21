package br.com.healthmonitor.controller

import br.com.healthmonitor.security.JwtTokenUtil
import br.com.healthmonitor.security.UserDetailsServiceImpl
import br.com.healthmonitor.dto.LoginRequestDTO
import br.com.healthmonitor.dto.JwtResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val jwtTokenUtil: JwtTokenUtil,
    private val userDetailsService: UserDetailsServiceImpl
) {

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequestDTO): ResponseEntity<JwtResponse> {
        val username = loginRequest.username
        val token = jwtTokenUtil.generateToken(username)
        return ResponseEntity.ok(JwtResponse(token))
    }
}
