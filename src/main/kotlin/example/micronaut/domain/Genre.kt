package example.micronaut.domain;

import com.fasterxml.jackson.annotation.JsonIgnore

import javax.validation.constraints.NotNull
import javax.persistence.*

@Entity
@Table(name = "genre")
data class Genre(@Id
                 @GeneratedValue(strategy = GenerationType.AUTO)
                 val id: Long,
                 @NotNull
                 @Column(name = "name", nullable = false, unique = true)
                 var name: String,
                 @JsonIgnore
                 @OneToMany(mappedBy = "genre")
                 val books: Set<Book>
)