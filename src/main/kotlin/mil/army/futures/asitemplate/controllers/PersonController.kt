package mil.army.futures.asitemplate.controllers

import mil.army.futures.asitemplate.PersonDTO
import mil.army.futures.asitemplate.services.PersonService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PersonController(private val personService: PersonService) {
    @PostMapping("/createPerson")
    fun createPerson(@RequestBody personName: String): PersonDTO {
        return personService.createPerson(personName)
    }

    @GetMapping("/person")
    fun getPeople(): List<PersonDTO> {
        return personService.getPeople()
    }

}