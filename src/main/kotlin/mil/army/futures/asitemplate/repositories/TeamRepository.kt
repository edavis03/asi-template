package mil.army.futures.asitemplate.repositories

import org.springframework.data.jpa.repository.JpaRepository

interface TeamRepository : JpaRepository<String, Long>
