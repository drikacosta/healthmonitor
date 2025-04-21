package br.com.healthmonitor.controller

import UserDetailsServiceImpl
import br.com.healthmonitor.dto.JwtResponse
import br.com.healthmonitor.dto.LoginRequestDTO
import br.com.healthmonitor.model.Device
import br.com.healthmonitor.model.HealthRecord
import br.com.healthmonitor.model.HealthType
import br.com.healthmonitor.model.User
import br.com.healthmonitor.security.JwtTokenUtil
import br.com.healthmonitor.service.DeviceService
import br.com.healthmonitor.service.HealthRecordService
import br.com.healthmonitor.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val jwtTokenUtil: JwtTokenUtil,
    private val userDetailsService: UserDetailsServiceImpl,
    private val passwordEncoder: PasswordEncoder
) {

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequestDTO): ResponseEntity<JwtResponse> {
        val userDetails = userDetailsService.loadUserByUsername(loginRequest.username)

        if (!passwordEncoder.matches(loginRequest.password, userDetails.password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }

        val token = jwtTokenUtil.generateToken(userDetails.username)
        return ResponseEntity.ok(JwtResponse(token))
    }
}

@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @PostMapping
    fun create(@RequestBody user: User): User = userService.create(user)

    @GetMapping("/by-email")
    fun findByEmail(@RequestParam email: String): User? = userService.getByEmail(email)
}

@RequestMapping("/api/devices")
class DeviceController(
    private val deviceService: DeviceService,
    private val userService: UserService
) {
    @GetMapping("/user/{email}")
    fun getDevicesByUser(@PathVariable email: String): List<Device>? {
        val user = userService.getByEmail(email) ?: return null
        return deviceService.findByUser(user)
    }
}

data class HealthRecordDTO(
    val email: String,
    val tipo: HealthType,
    val valor1: Double,
    val valor2: Double?,
    val observacao: String?
)

@RestController
@RequestMapping("/api/records")
class HealthRecordController(
    private val service: HealthRecordService,
    private val userService: UserService
) {
    @PostMapping
    fun create(@RequestBody dto: HealthRecordDTO): HealthRecord? {
        val user = userService.getByEmail(dto.email) ?: return null
        val record = HealthRecord(
            tipo = dto.tipo,
            valor1 = dto.valor1,
            valor2 = dto.valor2,
            observacao = dto.observacao,
            user = user
        )
        return service.save(record)
    }
}
