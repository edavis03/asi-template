package mil.army.futures.asitemplate

import javax.persistence.*

@Entity
data class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var firstName: String,
    var lastName: String,
    @ManyToOne
    var team: Team = Team(name = "Unallocated")
)
