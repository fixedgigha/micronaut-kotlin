package example.micronaut.domain

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "book")
data class Book (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    @NotNull
    @Column(name = "name", nullable = false)
    val name: String,
    @NotNull
    @Column(name = "isbn", nullable = false)
    val isbn: String,
    @ManyToOne
    private val genre: Genre
)