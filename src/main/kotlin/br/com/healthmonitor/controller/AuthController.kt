package br.com.healthmonitor.controller

import br.com.healthmonitor.dto.HealthRecordDTO
import br.com.healthmonitor.dto.JwtResponse
import br.com.healthmonitor.dto.LoginRequestDTO
import br.com.healthmonitor.model.*
import br.com.healthmonitor.repository.FrequenciaCardiacaRepository
import br.com.healthmonitor.repository.GlicemiaRepository
import br.com.healthmonitor.repository.PressaoArterialRepository
import br.com.healthmonitor.security.JwtTokenUtil
import br.com.healthmonitor.security.UserDetailsServiceImpl
import br.com.healthmonitor.service.DeviceService
import br.com.healthmonitor.service.HealthRecordService
import br.com.healthmonitor.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/auth")
class AuthController(
    private val jwtTokenUtil: JwtTokenUtil,
    private val userDetailsService: UserDetailsServiceImpl,
    private val passwordEncoder: PasswordEncoder
) {

    @PostMapping("/login")
    fun login(@Valid @RequestBody loginRequest: LoginRequestDTO): ResponseEntity<JwtResponse> {
        val userDetails = userDetailsService.loadUserByUsername(loginRequest.username)

        if (!passwordEncoder.matches(loginRequest.password, userDetails.password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }

        val token = jwtTokenUtil.generateToken(userDetails.username)
        return ResponseEntity.ok(JwtResponse(token))
    }
}
@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @PostMapping
    fun create(@RequestBody user: User): User = userService.create(user)

    @GetMapping("/by-email")
    fun findByEmail(@RequestParam email: String): User? = userService.getByEmail(email)
}
@RestController
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
            tipo = dto.type,
            valor1 = dto.valor1,
            valor2 = dto.valor2,
            observacao = dto.observacao,
            user = user
        )
        return service.save(record)
    }

    @RestController
    @RequestMapping("/api/glicemia")
    class GlicemiaController(
        private val repo: GlicemiaRepository,
        private val userService: UserService
    ) {

        @PostMapping("/manual")
        fun registrar(@RequestBody glicemia: GlicemiaManual) = repo.save(glicemia)

        @GetMapping("/historico/{email}")
        fun historico(@PathVariable email: String): List<GlicemiaManual>? {
            val user = userService.getByEmail(email) ?: return null
            return repo.findByUser_Id(user)
        }
    }

    @RestController
    @RequestMapping("/api/frequencia")
    class FrequenciaController(
        val repo: FrequenciaCardiacaRepository,
        val userService: UserService
    ) {

        @PostMapping("/iot")
        fun registrar(@RequestBody freq: FrequenciaCardiaca) = repo.save(freq)

        @GetMapping("/historico/{usuarioCardiacaId}")
        fun historico(@PathVariable usuarioCardiacaId: UUID): List<FrequenciaCardiaca>? {
            val user = userService.getById(usuarioCardiacaId) ?: return null
            return repo.findByUser(user)
        }
    }

        @RestController
        @RequestMapping("/api/pressao")
        class PressaoController(
            val repo: PressaoArterialRepository,
            val userService: UserService
        ) {

            @PostMapping("/iot")
            fun registrar(@RequestBody pressao: PressaoArterial) = repo.save(pressao)

            @GetMapping("/historico/{usuarioArterialId}")
            fun historico(@PathVariable usuarioArterialId: UUID): List<PressaoArterial>? {
                val user = userService.getById(usuarioArterialId) ?: return null
                return repo.findByUserId(user)
            }
        }
}
