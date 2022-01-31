package mil.army.futures.asitemplate.repositories

import mil.army.futures.asitemplate.Person
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository: JpaRepository<Person, Long>