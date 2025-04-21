package br.com.healthmonitor.repository

import br.com.healthmonitor.model.Device
import br.com.healthmonitor.model.HealthRecord
import br.com.healthmonitor.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, String> {
    fun findByEmail(email: String): User?
}

interface DeviceRepository : JpaRepository<Device, String> {
    fun findAllByUser(user: User): List<Device>
}

interface HealthRecordRepository : JpaRepository<HealthRecord, String> {
    fun findAllByUser(user: User): List<HealthRecord>
}