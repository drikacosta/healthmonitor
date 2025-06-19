//package br.com.healthmonitor.repository
//
//import br.com.healthmonitor.model.HealthRecord
//import br.com.healthmonitor.model.User
//import org.junit.jupiter.api.Assertions.assertEquals
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
//import java.time.LocalDateTime
//import java.util.*
//
//@DataJpaTest
//class HealthRecordRepositoryTest @Autowired constructor(
//    val healthRecordRepository: HealthRecordRepository,
//    val userRepository: UserRepository
//) {
//
//    @Test
//    fun `deve encontrar registros de saude por usuario`() {
//        val user = userRepository.save(User(UUID.randomUUID(), "john", "john@email.com", "1234"))
//
//        val record = HealthRecord(id = "r1", user = user, tipo = "pressao", valor = "120/80", dataHora = LocalDateTime.now())
//        healthRecordRepository.save(record)
//
//        val records = healthRecordRepository.findAllByUser(user)
//        assertEquals(1, records.size)
//        assertEquals("pressao", records[0].tipo)
//    }
//}
