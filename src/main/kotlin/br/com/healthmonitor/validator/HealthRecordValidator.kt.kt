package br.com.healthmonitor.validator

import br.com.healthmonitor.dto.HealthRecordDTO
import br.com.healthmonitor.dto.HealthType
import org.springframework.stereotype.Component


@Component
class HealthRecordValidator {
    fun validate(dto: HealthRecordDTO) {
        when (dto.type) {
            HealthType.pressao_arterial -> {
                requireNotNull(dto.valor1) { "Valor1 (sistólica) não pode ser nulo" }
                requireNotNull(dto.valor2) { "Valor2 (diastólica) não pode ser nulo" }

                require(dto.valor1 in 60.0..250.0) { "Pressão sistólica inválida (60-250)" }
                require(dto.valor2 in 40.0..150.0) { "Pressão diastólica inválida (40-150)" }
            }

            HealthType.glicemia -> {
                requireNotNull(dto.valor1) { "Valor1 (glicemia) não pode ser nulo" }
                require(dto.valor1 in 30.0..600.0) { "Glicemia inválida (30-600)" }
            }

            HealthType.frequencia_cardiaca -> {
                requireNotNull(dto.valor1) { "Valor1 (frequência cardíaca) não pode ser nulo" }
                require(dto.valor1 in 30.0..220.0) { "Frequência cardíaca inválida (30-220)" }
            }
        }
    }
}
