package br.com.healthmonitor.controller

import br.com.healthmonitor.dto.HealthRecordDTO
import br.com.healthmonitor.service.HealthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/registro")
class HealthController(
    private val service: HealthService
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