package mil.army.futures.asitemplate.services

import mil.army.futures.asitemplate.Person
import mil.army.futures.asitemplate.PersonDTO
import mil.army.futures.asitemplate.Team
import mil.army.futures.asitemplate.repositories.PersonRepository
import org.springframework.stereotype.Service

@Service
class PersonService(private val personRepository: PersonRepository) {
    fun getPeople(): List<PersonDTO> {
        val allPeople = personRepository.findAll()

        return allPeople.map { person -> PersonDTO(id = person.id, name = person.name, teamId = person.teamId.id) }
    }

    fun createPerson(personName: String): PersonDTO {
        val newPerson = personRepository.save(Person(name = personName, teamId = Team(name = "Unallocated")))

        return PersonDTO(id = newPerson.id, name = newPerson.name, teamId = newPerson.teamId.id)
    }
}