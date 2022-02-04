package mil.army.futures.asitemplate.controllers

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import mil.army.futures.asitemplate.PersonDTO
import mil.army.futures.asitemplate.services.PersonService
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@WebMvcTest(controllers = [PersonController::class])
internal class PersonControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var personService: PersonService

    @Test
    fun `when creating a person it delegates to PersonService`() {
        every {
            personService.createPerson("Josh White")

        } returns PersonDTO(id = 1L, name = "Josh White", teamId = 1)

        mockMvc.post("/createPerson") {
            contentType = MediaType.TEXT_PLAIN
            content = "Josh White"
        }.andDo { print() }.andExpect {
            status { isOk() }
            content { string(CoreMatchers.containsString("Josh White")) }
        }

        verify(exactly = 1) {
            personService.createPerson("Josh White")
        }
    }

    @Test
    fun `when getting people it delegates to PeopleRepository`() {
        every { personService.getPeople() } returns listOf(
            PersonDTO(id = 1L, name = "Easton White", teamId = 1),
            PersonDTO(id = 2L, name = "Colton White", teamId = 1)
        )

        mockMvc.get("/person")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { string(CoreMatchers.containsString("Easton White")) }
                content { string(CoreMatchers.containsString("Colton White")) }
            }

        verify(exactly = 1) { personService.getPeople() }
    }
}