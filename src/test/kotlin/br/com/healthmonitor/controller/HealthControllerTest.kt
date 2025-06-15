package br.com.healthmonitor.controller

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class HealthControllerTest {

    private val healthController = HealthController()

    @Test
    fun `healthcheck should return status UP`() {
        val response: ResponseEntity<Map<String, String>> = healthController.healthcheck()

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals("UP", response.body?.get("status"))
    }
}
