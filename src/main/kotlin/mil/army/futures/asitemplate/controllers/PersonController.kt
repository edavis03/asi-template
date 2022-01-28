package mil.army.futures.asitemplate.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PersonController() {
    @GetMapping("/people")
    fun getPerson(): List<Person> {

    }
}