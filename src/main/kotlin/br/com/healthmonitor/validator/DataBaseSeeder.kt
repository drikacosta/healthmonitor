package br.com.healthmonitor.validator

import br.com.healthmonitor.model.*
import br.com.healthmonitor.repository.*
import jakarta.annotation.PostConstruct
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class DatabaseSeeder(
    private val userRepository: UserRepository,
    private val deviceRepository: DeviceRepository,
    private val glicemiaRepository: GlicemiaRepository,
    private val freqRepository: FrequenciaCardiacaRepository,
    private val pressaoRepository: PressaoArterialRepository
) {

    private val passwordEncoder = BCryptPasswordEncoder()

    @PostConstruct
    fun seed() {
        // Cria usuário
        val user = User(
            name = "Teste Usuário",
            email = "teste@exemplo.com",
            password = passwordEncoder.encode("senha123")
        )
        userRepository.save(user)

        // Cria dispositivo para o usuário
        val device = Device(
            nome = "Monitor Cardíaco 1",
            tipo = "Cardíaco",
            macAddress = "00:11:22:33:44:55",
            status = true,
            ultimaSync = LocalDateTime.now().minusDays(1),
            user = user
        )
        deviceRepository.save(device)

        // Cria glicemia manual
        val glicemia = GlicemiaManual(
            valorGlicemia = 98.5,
            dataHora = LocalDateTime.now().minusHours(3),
            user = user
        )
        glicemiaRepository.save(glicemia)

        // Cria frequência cardíaca
        val frequencia = FrequenciaCardiaca(
            batimentos = 75,
            dataHora = LocalDateTime.now().minusHours(2),
            dispositivoId = device.id,
            user = user
        )
        freqRepository.save(frequencia)

        // Cria pressão arterial
        val pressao = PressaoArterial(
            sistolica = 120,
            diastolica = 80,
            dataHora = LocalDateTime.now().minusHours(1),
            user = user
        )
        pressaoRepository.save(pressao)

        println("Dados mock inseridos com sucesso!")
    }
}