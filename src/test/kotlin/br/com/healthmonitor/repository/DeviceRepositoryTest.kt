//package br.com.healthmonitor.repository
//
//import br.com.healthmonitor.model.Device
//import br.com.healthmonitor.model.User
//import org.junit.jupiter.api.Assertions.*
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
//import java.util.*
//
//@DataJpaTest
//class DeviceRepositoryTest @Autowired constructor(
//    val deviceRepository: DeviceRepository,
//    val userRepository: UserRepository
//) {
//
//    @Test
//    fun `deve encontrar dispositivos por usuario`() {
//        val user = userRepository.save(
//            User(UUID.randomUUID(), "user1", "user1@email.com", "1234")
//        )
//
//        val device1 = Device(id = "dev1", nome = "Smartwatch", user = user)
//        val device2 = Device(id = "dev2", nome = "Pulseira", user = user)
//        deviceRepository.saveAll(listOf(device1, device2))
//
//        val devices = deviceRepository.findAllByUser(user)
//
//        assertEquals(2, devices.size)
//    }
//}
