package br.com.healthmonitor.repository

import br.com.healthmonitor.model.FrequenciaCardiaca
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.LocalDateTime

@DataJpaTest
class FrequenciaCardiacaRepositoryTest @Autowired constructor(
    val frequenciaCardiacaRepository: FrequenciaCardiacaRepository
) {

    @Test
    fun `deve buscar frequencias cardiacas por usuarioCardiacaId`() {
        val usuarioCardiacaId = "100"

        val frequencia1 = FrequenciaCardiaca(
            id = null,
            usuarioPressaoId = usuarioCardiacaId,
            valor = 72,
            timestamp = LocalDateTime.now()
        )

        val frequencia2 = FrequenciaCardiaca(
            id = null,
            usuarioPressaoId = usuarioCardiacaId,
            valor = 80,
            timestamp = LocalDateTime.now().minusHours(1)
        )

        frequenciaCardiacaRepository.saveAll(listOf(frequencia1, frequencia2))

        val lista = frequenciaCardiacaRepository.findByUser(usuarioCardiacaId)

        assertEquals(2, lista.size)
        assertEquals(72, lista[0].valor)
        assertEquals(80, lista[1].valor)
    }
}
