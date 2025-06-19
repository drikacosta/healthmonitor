package br.com.healthmonitor.service

import br.com.healthmonitor.model.HealthRecord
import br.com.healthmonitor.model.User
import br.com.healthmonitor.repository.HealthRecordRepository
import org.springframework.stereotype.Service

@Service
class HealthRecordService(private val repo: HealthRecordRepository) {
    fun findByUser(user: User): List<HealthRecord> = repo.findAllByUser(user)
    fun save(record: HealthRecord): HealthRecord = repo.save(record)
}