package mil.army.futures.asitemplate.repositories

import mil.army.futures.asitemplate.Team
import org.springframework.data.jpa.repository.JpaRepository

interface TeamRepository : JpaRepository<Team, Long>
