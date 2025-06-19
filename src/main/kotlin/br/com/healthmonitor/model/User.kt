package br.com.healthmonitor.model

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "users")
data class User(
    @Id val id: String = UUID.randomUUID().toString(),
    @Column(unique = true, nullable = false)
    val name: String,
    @Column(unique = true, nullable = false)
    val email: String,
    @Column(nullable = false)
    val password: String,
    @Column(name = "created_at") val createdAt: LocalDateTime = LocalDateTime.now()
)

@Entity
@Table(name = "devices")
data class Device(
    @Id val id: String = UUID.randomUUID().toString(),

    val nome: String,
    val tipo: String?,
    @Column(name = "mac_address") val macAddress: String?,
    val status: Boolean = true,

    @Column(name = "ultima_sync") val ultimaSync: LocalDateTime? = null,

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id") val user: User
)

@Entity
@Table(name = "health_records")
data class HealthRecord(
    @Id val id: String = UUID.randomUUID().toString(),

    @Enumerated(EnumType.STRING) val tipo: HealthType,

    val valor1: Double, val valor2: Double? = null, val observacao: String? = null,

    @Column(name = "data_hora") val dataHora: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id") val user: User
)

enum class HealthType {
    frequencia_cardiaca, pressao_arterial, glicemia
}

@Entity
@Table(name = "glicemia_manual")
data class GlicemiaManual(
    @Id
    val id: String = UUID.randomUUID().toString(),
    val valorGlicemia: Double,
    val dataHora: LocalDateTime,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User
)

@Entity
@Table(name = "frequencia_cardiaca")
data class FrequenciaCardiaca(
    @Id
    val id: String = UUID.randomUUID().toString(),
    val batimentos: Int,
    val dataHora: LocalDateTime,
    val dispositivoId: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User
)

@Entity
@Table(name = "pressao_arterial")
data class PressaoArterial(
    @Id
    val id: String = UUID.randomUUID().toString(),
    val sistolica: Int,
    val diastolica: Int,
    val dataHora: LocalDateTime,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User
)

data class JwtResponse(
    val token: String
)