package mil.army.futures.asitemplate.service

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import mil.army.futures.asitemplate.Team
import mil.army.futures.asitemplate.repositories.TeamRepository
import mil.army.futures.asitemplate.services.TeamService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
internal class TeamServiceTest {
    @MockkBean
    private lateinit var teamRepository: TeamRepository

    private lateinit var teamService: TeamService

    @BeforeEach
    fun setUp() {
        teamService = TeamService(teamRepository)
    }

    @Test
    fun `should retrieve all teams`() {
        val expectedTeams = listOf(Team(1L, "first-team"), Team(2L, "second-team"))
        every { teamRepository.findAll() } returns expectedTeams

        val actualTeams: List<Team> = teamService.getTeams()

        assertThat(actualTeams).isEqualTo(expectedTeams)
    }

    @Test
    fun `should create a new team`() {
        every { teamRepository.save(any()) } answers { firstArg() }
        val teamToSave = Team(1L, "first-team")

        teamService.createTeam(teamToSave)

        verify { teamRepository.save(teamToSave) }
    }
}
