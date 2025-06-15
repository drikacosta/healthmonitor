package br.com.healthmonitor.service

import br.com.healthmonitor.dto.HealthRecordDTO
import br.com.healthmonitor.dto.HealthType
import br.com.healthmonitor.model.Device
import br.com.healthmonitor.model.FrequenciaCardiaca
import br.com.healthmonitor.model.GlicemiaManual
import br.com.healthmonitor.model.HealthRecord
import br.com.healthmonitor.model.PressaoArterial
import br.com.healthmonitor.model.User
import br.com.healthmonitor.repository.DeviceRepository
import br.com.healthmonitor.repository.FrequenciaCardiacaRepository
import br.com.healthmonitor.repository.GlicemiaRepository
import br.com.healthmonitor.repository.HealthRecordRepository
import br.com.healthmonitor.repository.PressaoArterialRepository
import br.com.healthmonitor.repository.UserRepository
import br.com.healthmonitor.validator.HealthRecordValidator
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(private val userRepository: UserRepository) {
    fun getByEmail(email: String): User? = userRepository.findByEmail(email)
    fun getById(id: UUID): User? = userRepository.findById(id).orElse(null)
    fun create(user: User): User = userRepository.save(user)
}

@Service
class DeviceService(private val repo: DeviceRepository) {
    fun findByUser(user: User): List<Device> = repo.findAllByUser(user)
    fun save(device: Device): Device = repo.save(device)
}

@Service
class HealthRecordService(private val repo: HealthRecordRepository) {
    fun findByUser(user: User): List<HealthRecord> = repo.findAllByUser(user)
    fun save(record: HealthRecord): HealthRecord = repo.save(record)
}

@Service
class HealthService(
    private val validator: HealthRecordValidator,
    private val glicemiaRepo: GlicemiaRepository,
    private val freqRepo: FrequenciaCardiacaRepository,
    private val pressaoRepo: PressaoArterialRepository,
    private val userService: UserService
) {

    fun registrar(dto: HealthRecordDTO) {
        validator.validate(dto)

        val user = userService.getById(dto.userId as UUID)
            ?: throw IllegalArgumentException("Usuário com o id ${dto.userId} não encontrado")

        when (dto.type) {
            HealthType.glicemia -> {
                val entity = GlicemiaManual(
                    valorGlicemia = dto.valor1,
                    dataHora = dto.timestamp,
                    user = user
                )
                glicemiaRepo.save(entity)
            }

            HealthType.pressao_arterial -> {
                val entity = PressaoArterial(
                    sistolica = dto.valor1.toInt(),
                    diastolica = dto.valor2?.toInt() ?: 0,
                    dataHora = dto.timestamp,
                    user = user
                )
                pressaoRepo.save(entity)
            }

            HealthType.frequencia_cardiaca -> {
                val entity = FrequenciaCardiaca(
                    batimentos = dto.valor1.toInt(),
                    dataHora = dto.timestamp,
                    dispositivoId = "manual", // ou vindo do DTO
                    user= user
                )
                freqRepo.save(entity)
            }
        }
    }
}