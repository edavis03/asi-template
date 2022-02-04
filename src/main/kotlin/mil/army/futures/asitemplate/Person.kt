package mil.army.futures.asitemplate

import javax.persistence.*

@Entity
data class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    @ManyToOne
    val teamId: Team
)

