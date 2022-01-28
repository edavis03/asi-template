package mil.army.futures.asitemplate.controllers

import mil.army.futures.asitemplate.repositories.PersonRepository
import org.springframework.web.bind.annotation.RestController

@RestController
class PersonController(private val personRepository: PersonRepository) {
}