package mil.army.futures.asitemplate.services

import mil.army.futures.asitemplate.Person
import mil.army.futures.asitemplate.PersonDTO
import mil.army.futures.asitemplate.Team
import mil.army.futures.asitemplate.repositories.PersonRepository
import mil.army.futures.asitemplate.repositories.TeamRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PersonService(private val personRepository: PersonRepository, private val teamRepository: TeamRepository) {
    fun getPeople(): List<PersonDTO> {
        val allPeople = personRepository.findAll()

        return allPeople.map { person -> PersonDTO(id = person.id, name = person.name, teamId = person.team.id) }
    }

    fun createPerson(personName: String): PersonDTO {
        val newPerson = personRepository.save(Person(name = personName))

        return PersonDTO(id = newPerson.id, name = newPerson.name, teamId = newPerson.team.id)
    }

    fun changeTeams(personId: Long, teamId: Long): PersonDTO {
        val currentPerson = personRepository.findByIdOrNull(personId) ?: error("Person Does not exist")
        val newTeam = teamRepository.findByIdOrNull(teamId) ?: error("New team does not exist")

        val personWithNewTeam = Person(currentPerson.id, currentPerson.name, newTeam)
        personRepository.save(personWithNewTeam)

        return PersonDTO(personWithNewTeam.id, personWithNewTeam.name, personWithNewTeam.team.id)

    }
}