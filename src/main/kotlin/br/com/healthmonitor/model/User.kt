package br.com.healthmonitor.model

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "users")
data class User(

    @Id
    val id: String = UUID.randomUUID().toString(),

    val name: String,

    @Column(unique = true)
    val email: String,

    @Column(name = "password_hash")
    val passwordHash: String,

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now()
)

@Entity
@Table(name = "devices")
data class Device(
    @Id
    val id: String = UUID.randomUUID().toString(),

    val nome: String,
    val tipo: String?,
    @Column(name = "mac_address")
    val macAddress: String?,
    val status: Boolean = true,

    @Column(name = "ultima_sync")
    val ultimaSync: LocalDateTime? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User
)

@Entity
@Table(name = "health_records")
data class HealthRecord(
    @Id
    val id: String = UUID.randomUUID().toString(),

    @Enumerated(EnumType.STRING)
    val tipo: HealthType,

    val valor1: Double,
    val valor2: Double? = null,
    val observacao: String? = null,

    @Column(name = "data_hora")
    val dataHora: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User
)

enum class HealthType {
    frequencia_cardiaca, pressao_arterial, glicemia
}
