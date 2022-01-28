package mil.army.futures.asitemplate.controllers

import com.ninjasquad.springmockk.MockkBean
import mil.army.futures.asitemplate.repositories.PersonRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc

@WebMvcTest
@AutoConfigureMockMvc
internal class PersonControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var personRepository: PersonRepository

    @Test
    fun `when getting people it delegates to PersonRepository`() {

    }
}