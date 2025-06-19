package br.com.healthmonitor.controller

import br.com.healthmonitor.dto.HealthRecordDTO
import br.com.healthmonitor.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/registro")
class HealthController(
    private val service: UserService.HealthService
) {

    @PostMapping
    fun registrar(@RequestBody dto: HealthRecordDTO): ResponseEntity<String> {
        return try {
            service.registrar(dto)
            ResponseEntity.ok("Registro salvo com sucesso")
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
}