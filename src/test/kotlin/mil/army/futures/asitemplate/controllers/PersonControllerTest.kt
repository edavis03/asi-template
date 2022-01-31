package mil.army.futures.asitemplate.controllers

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import mil.army.futures.asitemplate.Person
import mil.army.futures.asitemplate.Team
import mil.army.futures.asitemplate.repositories.PersonRepository
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@WebMvcTest(controllers = [PersonController::class])
@AutoConfigureMockMvc
internal class PersonControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var mockPersonRepository: PersonRepository

    @Test
    fun `when creating a person it delegates to PersonRepository`() {
        every { mockPersonRepository.save(Person(name = "Josh White")) } returns Person(id = 1L, name = "Josh White")

        mockMvc.post("/createPerson") {
            contentType = MediaType.TEXT_PLAIN
            content = "Josh White"
        }.andDo { print() }.andExpect {
            status { isOk() }
            content { string(CoreMatchers.containsString("Josh White")) }
        }

        verify(exactly = 1) {
            mockPersonRepository.save(Person(name = "Josh White"))
        }
    }

    @Test
    fun `when getting people it delegates to PeopleRepository`() {
        every { mockPersonRepository.findAll() } returns listOf(
            Person(id = 1L, name = "Colton White"),
            Person(id = 2L, name = "Easton White")
        )

        mockMvc.get("/people")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { string(CoreMatchers.containsString("Colton White")) }
                content { string(CoreMatchers.containsString("Easton White")) }
            }

        verify(exactly = 1) { mockPersonRepository.findAll() }
    }

}