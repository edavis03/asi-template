package mil.army.futures.asitemplate.controllers

import mil.army.futures.asitemplate.Person
import mil.army.futures.asitemplate.repositories.PersonRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PersonController(private val personRepository: PersonRepository) {

    @GetMapping("/person")
    fun getPeople(): List<Person> {
        return personRepository.findAll()
    }

    @PostMapping("/person")
    fun createPerson(@RequestBody  person: Person): Person {
        return personRepository.save(person)
    }
}