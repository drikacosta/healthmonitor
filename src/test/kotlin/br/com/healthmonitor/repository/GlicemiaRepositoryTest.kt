//import br.com.healthmonitor.model.GlicemiaManual
//import br.com.healthmonitor.repository.GlicemiaRepository
//import org.junit.jupiter.api.Assertions.assertEquals
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
//import java.time.LocalDateTime
//
//@DataJpaTest
//class GlicemiaRepositoryTest @Autowired constructor(
//    val glicemiaRepository: GlicemiaRepository
//) {
//    @Test
//    fun `deve buscar glicemias por usuarioId`() {
//        val glicemia = GlicemiaManual( usuarioGlicemiaId = "10", valorGlicemia = 95.0, dataHora = LocalDateTime.now())
//        glicemiaRepository.save(glicemia)
//
//        val lista = glicemiaRepository.findByUsuarioId(10L)
//        assertEquals(1, lista.size)
//    }
//}
