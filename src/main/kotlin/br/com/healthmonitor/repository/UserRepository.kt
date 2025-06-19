package br.com.healthmonitor.repository

import br.com.healthmonitor.model.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, UUID> {
    fun findByEmail(email: String): User?
}

interface DeviceRepository : JpaRepository<Device, String> {
    fun findAllByUser(user: User): List<Device>
}

interface HealthRecordRepository : JpaRepository<HealthRecord, String> {
    fun findAllByUser(user: User): List<HealthRecord>
}

interface GlicemiaRepository : JpaRepository<GlicemiaManual, String> {
    fun findByUser_Id(user: User): List<GlicemiaManual>
}

interface FrequenciaCardiacaRepository : JpaRepository<FrequenciaCardiaca, String> {
    fun findByUser(user: User): List<FrequenciaCardiaca>
}

interface PressaoArterialRepository : JpaRepository<PressaoArterial, String> {
    fun findByUserId(user: User): List<PressaoArterial>
}