//package br.com.healthmonitor.controller
//
//import br.com.healthmonitor.dto.LoginRequestDTO
//import br.com.healthmonitor.security.JwtTokenUtil
//
//import br.com.healthmonitor.service.UserDetailsServiceImpl
//import br.com.healthmonitor.service.UserService
//import org.junit.jupiter.api.Assertions.assertEquals
//import org.junit.jupiter.api.Test
//import org.mockito.Mockito.mock
//import org.mockito.Mockito.`when`
//import org.springframework.http.HttpStatus
//import org.springframework.security.core.userdetails.User
//import org.springframework.security.crypto.password.PasswordEncoder
//
//class AuthControllerTest {
//
//    private val userService = mock(UserService::class.java)
//    private val userDetailsService = mock(UserDetailsServiceImpl::class.java)
//    private val passwordEncoder = mock(PasswordEncoder::class.java)
//
//    private val authController = AuthController(user, authenticationManager = , passwordEncoder)
//
//    @Test
//    fun `login should return JWT when credentials are valid`() {
//        val username = "testuser"
//        val password = "password123"
//        val token = "mocked-jwt-token"
//
//        val loginRequest = LoginRequestDTO(username, password)
//
//        val userDetails = User(username, password, listOf())
//
//        `when`(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails)
//        `when`(passwordEncoder.matches(password, userDetails.password)).thenReturn(true)
//        `when`(jwtTokenUtil.generateToken(userDetails)).thenReturn(token)
//
//        val response = authController.login(loginRequest)
//
//        assertEquals(HttpStatus.OK, response.statusCode)
//        assertEquals(token, response.body?.token)
//    }
//
//    @Test
//    fun `login should return Unauthorized when credentials are invalid`() {
//        val username = "testuser"
//        val password = "wrongpassword"
//        val loginRequest = LoginRequestDTO(username, password)
//
//        val userDetails = User(username, "correctpassword", listOf())
//
//        `when`(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails)
//        `when`(passwordEncoder.matches(password, userDetails.password)).thenReturn(false)
//
//        val response = authController.login(loginRequest)
//
//        assertEquals(HttpStatus.UNAUTHORIZED, response.statusCode)
//    }
//}
