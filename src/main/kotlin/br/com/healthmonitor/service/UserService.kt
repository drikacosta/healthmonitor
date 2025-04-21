package br.com.healthmonitor.service

import br.com.healthmonitor.model.Device
import br.com.healthmonitor.model.HealthRecord
import br.com.healthmonitor.model.User
import br.com.healthmonitor.repository.DeviceRepository
import br.com.healthmonitor.repository.HealthRecordRepository
import br.com.healthmonitor.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun getByEmail(email: String): User? = userRepository.findByEmail(email)

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