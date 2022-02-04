package mil.army.futures.asitemplate.services

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import mil.army.futures.asitemplate.Person
import mil.army.futures.asitemplate.PersonDTO
import mil.army.futures.asitemplate.Team
import mil.army.futures.asitemplate.repositories.PersonRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class PersonServiceTest {

    private lateinit var personService: PersonService

    @MockkBean
    private lateinit var mockPersonRepository: PersonRepository

    @BeforeEach
    fun Setup() {
        mockPersonRepository = mockk()
        personService = PersonService(mockPersonRepository)
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
        verify(exactly = 1) { mockPersonRepository.save(Person(
            name = "Josh White"
        )) }
    }
}