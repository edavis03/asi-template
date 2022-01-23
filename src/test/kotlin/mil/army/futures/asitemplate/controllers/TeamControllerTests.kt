package mil.army.futures.asitemplate.controllers

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import mil.army.futures.asitemplate.Team
import mil.army.futures.asitemplate.repositories.PersonRepository
import mil.army.futures.asitemplate.repositories.TeamRepository
import org.hamcrest.CoreMatchers.containsString
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
internal class TeamControllerTests {
    @Autowired
    private lateinit var mockMvc: MockMvc

    //TODO Not sure why I had to add this? Is it because I added a manyToOne Relationship in person?
    @MockkBean
    private lateinit var personRepository: PersonRepository

    @MockkBean
    private lateinit var teamRepository: TeamRepository

    @Test
    fun `when creating teams it delegates to TeamRepository`() {
        every { teamRepository.save(Team(name = "first-team-name")) } returns Team(id = 1L, name = "first-team-name")

        mockMvc.post("/team") {
            contentType = MediaType.TEXT_PLAIN
            content = "first-team-name"
        }.andDo { print() }.andExpect {
            status { isOk() }
            content { string(containsString("first-team-name")) }
        }

        verify(exactly = 1) {
            teamRepository.save(Team(name = "first-team-name"))
        }
    }

    @Test
    fun `when getting teams it delegates to TeamRepository`() {
        every { teamRepository.findAll() } returns listOf(
            Team(id = 1L, name = "first-team-name"),
            Team(id = 2L, name = "second-team-name")
        )

        mockMvc.get("/teams")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { string(containsString("first-team-name")) }
                content { string(containsString("second-team-name")) }
            }

        verify(exactly = 1) { teamRepository.findAll() }
    }

}