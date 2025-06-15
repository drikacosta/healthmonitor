package br.com.healthmonitor.repository

import br.com.healthmonitor.model.User
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.util.*

@DataJpaTest
class UserRepositoryTest @Autowired constructor(
    val userRepository: UserRepository
) {

    @Test
    fun `deve salvar e buscar usuario por email e username`() {
        val user = User(
            id = UUID.randomUUID(),
            username = "johndoe",
            email = "johndoe@example.com",
            password = "123456"
        )

        userRepository.save(user)

        val userByEmail = userRepository.findByEmail("johndoe@example.com")
        val userByUsername = userRepository.findByUsername("johndoe")

        assertNotNull(userByEmail)
        assertEquals("johndoe", userByEmail?.username)

        assertNotNull(userByUsername)
        assertEquals("johndoe@example.com", userByUsername?.email)
    }
}
