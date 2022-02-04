package mil.army.futures.asitemplate

import javax.persistence.*

@Entity
data class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    @ManyToOne
    val team: Team = Team(id = 1, name = "Unallocated")
)

