//package br.com.healthmonitor.controller
//
//import br.com.healthmonitor.model.Device
//import br.com.healthmonitor.model.User
//import br.com.healthmonitor.service.DeviceService
//import br.com.healthmonitor.service.UserService
//import org.junit.jupiter.api.Test
//import org.mockito.Mockito.`when`
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
//import org.springframework.boot.test.mock.mockito.MockBean
//import org.springframework.http.MediaType
//import org.springframework.test.web.servlet.MockMvc
//import org.springframework.test.web.servlet.get
//
//@WebMvcTest(DeviceController::class)
//class DeviceControllerTest {
//
//    @Autowired
//    lateinit var mockMvc: MockMvc
//
//    @MockBean
//    lateinit var userService: UserService
//
//    @MockBean
//    lateinit var deviceService: DeviceService
//
//    @Test
//    fun `deve retornar lista de devices para um usuário existente`() {
//        // Arrange
//        val email = "teste@exemplo.com"
//        val user = User(id = 1L, name = "Usuário Teste", email = email, password = "123456")
//        val devices = listOf(
//            Device(id = 1L, name = "Smartphone", type = "Mobile", user = user),
//            Device(id = 2L, name = "Smartwatch", type = "Wearable", user = user)
//        )
//
//        `when`(userService.getByEmail(email)).thenReturn(user)
//        `when`(deviceService.findByUser(user)).thenReturn(devices)
//
//        // Act & Assert
//        mockMvc.get("/user/$email")
//            .andExpect {
//                status { isOk() }
//                content { contentType(MediaType.APPLICATION_JSON) }
//                jsonPath("$.size()") { value(2) }
//                jsonPath("$[0].name") { value("Smartphone") }
//                jsonPath("$[1].name") { value("Smartwatch") }
//            }
//    }
//
//    @Test
//    fun `deve retornar null quando usuário não for encontrado`() {
//        // Arrange
//        val email = "naoexiste@exemplo.com"
//        `when`(userService.getByEmail(email)).thenReturn(null)
//
//        // Act & Assert
//        mockMvc.get("/user/$email")
//            .andExpect {
//                status { isOk() } // Ou ajuste para 404 se quiser melhorar o endpoint depois
//                content { string("") }
//            }
//    }
//}
