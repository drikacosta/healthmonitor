package br.com.healthmonitor.dto

import java.time.LocalDateTime

data class HealthRecordDTO(
    val email: String,
    val type: HealthType,
    val valor1: Double,
    val valor2: Double? = null,
    val userId: String,
    val observacao: String? = null,
    val timestamp: LocalDateTime
)

enum class HealthType {
    pressao_arterial,
    glicemia,
    frequencia_cardiaca
}
