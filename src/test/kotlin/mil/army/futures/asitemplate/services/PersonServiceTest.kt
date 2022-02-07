package mil.army.futures.asitemplate.services

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import mil.army.futures.asitemplate.Person
import mil.army.futures.asitemplate.PersonDTO
import mil.army.futures.asitemplate.Team
import mil.army.futures.asitemplate.repositories.PersonRepository
import mil.army.futures.asitemplate.repositories.TeamRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull

internal class PersonServiceTest {

    private lateinit var personService: PersonService

    @MockkBean
    private lateinit var mockPersonRepository: PersonRepository

    @MockkBean
    private lateinit var mockTeamRepository: TeamRepository

    @BeforeEach
    fun Setup() {
        mockPersonRepository = mockk()
        mockTeamRepository = mockk()
        personService = PersonService(mockPersonRepository, mockTeamRepository)
    }

    @Test
    fun `when getting people it delegates to PersonRepository and returns personDTO`() {

        every { mockPersonRepository.findAll() } returns listOf(
            Person(id = 1L, name = "Easton White", Team(id = 1L, name = "Unallocated")),
            Person(id = 2L, name = "Colton White", Team(id = 2L, name = "Goat Team"))
        )

        val expectedReturn = listOf(
            PersonDTO(id = 1L, name = "Easton White", teamId = 1L),
            PersonDTO(id = 2L, name = "Colton White", teamId = 2L)
        )

        val result = personService.getPeople()

        assertThat(result).isEqualTo(expectedReturn)

        verify(exactly = 1) { mockPersonRepository.findAll() }
    }

    @Test
    fun `when creating it delegates to the PersonController, adds a default team and returns PersonDTO`() {
        every {
            mockPersonRepository.save(
                Person(
                    name = "Josh White"
                )
            )
        } returns Person(id = 1L, name = "Josh White", team = Team(id = 1, name = "Unallocated"))

        val expectedReturn = PersonDTO(id = 1L, name = "Josh White", teamId = 1L)

        val result = personService.createPerson("Josh White")

        assertThat(result).isEqualTo(expectedReturn)
        verify(exactly = 1) {
            mockPersonRepository.save(
                Person(
                    name = "Josh White"
                )
            )
        }
    }

    @Test
    fun `if a valid team changes a person to that team`() {
        every { mockPersonRepository.findByIdOrNull(1) } returns Person(
            id = 1L,
            name = "Fran",
            team = Team(id = 1L, name = "Unallocated")
        )

        every { mockTeamRepository.findByIdOrNull(2) } returns Team(
            id = 2L,
            name = "New Team"
        )

        val personWithNewTeam = Person(id = 1L, name = "Fran", team = Team(id = 2L, name = "New Team"))

        every { mockPersonRepository.save(personWithNewTeam) } returns personWithNewTeam

        val expectedReturn = PersonDTO(id = 1L, name = "Fran", teamId = 2L)

        val result = personService.changeTeams(1, 2)

        assertThat(result).isEqualTo(expectedReturn)

        verify(exactly = 1) { mockPersonRepository.save(any()) }
        verify(exactly = 1) { mockPersonRepository.findByIdOrNull(1) }
        verify(exactly = 1) { mockTeamRepository.findByIdOrNull(2) }
    }
}