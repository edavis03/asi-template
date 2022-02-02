package mil.army.futures.asitemplate.controllers

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import mil.army.futures.asitemplate.Team
import mil.army.futures.asitemplate.services.TeamService
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@WebMvcTest(TeamController::class)
internal class TeamControllerTests {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var teamService: TeamService

    @Test
    fun `should save a new team when a new team is created`() {
        every { teamService.createTeam(Team(name = "first-team-name")) } returns Team(id = 1L, name = "first-team-name")

        mockMvc.post("/teams") {
            contentType = MediaType.TEXT_PLAIN
            content = "first-team-name"
        }.andExpect {
            status { isCreated() }
            content { string(containsString("first-team-name")) }
        }

        verify(exactly = 1) {
            teamService.createTeam(Team(name = "first-team-name"))
        }
    }

    @Test
    fun `should retrieve all teams when getting teams`() {
        every { teamService.getTeams() } returns listOf(
            Team(id = 1L, name = "first-team-name"),
            Team(id = 2L, name = "second-team-name")
        )

        mockMvc.get("/teams").andExpect {
            status { isOk() }
            content { string(containsString("first-team-name")) }
            content { string(containsString("second-team-name")) }
        }

        verify(exactly = 1) { teamService.getTeams() }
    }

}
