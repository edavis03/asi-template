package mil.army.futures.asitemplate.controllers

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import mil.army.futures.asitemplate.Person
import mil.army.futures.asitemplate.Team
import mil.army.futures.asitemplate.repositories.PersonRepository
import mil.army.futures.asitemplate.repositories.TeamRepository
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@WebMvcTest
@AutoConfigureMockMvc
internal class PersonControllerTests {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var personRepository: PersonRepository

    @MockkBean
    private lateinit var teamRepository: TeamRepository

    @Test
    fun `when creating a person it delegates to PersonRepository`() {
        every { personRepository.save(Person(id = 0, firstName = "Josh", lastName = "White")) } returns Person(
            id = 1L,
            firstName = "Josh",
            lastName = "White",
            team = Team(id = 1, name = "Unallocated")
        )

        mockMvc.post("/person") {
            contentType = MediaType.APPLICATION_JSON
            content = ("{\"firstName\": \"Josh\", \"lastName\": \"White\", \"team\": {\"name\": \"Unallocated\"}}")
        }.andDo { print() }.andExpect {
            status { isOk() }
            content { string(CoreMatchers.containsString("{\"id\":1,\"firstName\":\"Josh\",\"lastName\":\"White\",\"team\":{\"id\":1,\"name\":\"Unallocated\"}}")) }
        }

        verify(exactly = 1) {
            personRepository.save(Person(firstName = "Josh", lastName = "White"))
        }
    }

    @Test
    fun `when getting people it delegates to PersonRepository`() {
        every { personRepository.findAll() } returns listOf(
            Person(id = 1L, firstName = "Josh", lastName = "White"),
            Person(id = 2L, firstName = "Colton", lastName = "White"),
            Person(id = 3L, firstName = "Easton", lastName = "White")
        )

        mockMvc.get("/person")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { string(CoreMatchers.containsString("{\"id\":1,\"firstName\":\"Josh\",\"lastName\":\"White\",\"team\":{\"id\":0,\"name\":\"Unallocated\"}}")) }
                content { string(CoreMatchers.containsString("{\"id\":2,\"firstName\":\"Colton\",\"lastName\":\"White\",\"team\":{\"id\":0,\"name\":\"Unallocated\"}}")) }
                content { string(CoreMatchers.containsString("{\"id\":3,\"firstName\":\"Easton\",\"lastName\":\"White\",\"team\":{\"id\":0,\"name\":\"Unallocated\"}}")) }
            }

        verify(exactly = 1) { personRepository.findAll() }
    }
}