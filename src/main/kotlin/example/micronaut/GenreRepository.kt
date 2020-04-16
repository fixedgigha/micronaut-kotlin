package example.micronaut

import example.micronaut.domain.Genre
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

interface GenreRepository {
    fun findById(id: Long): Optional<Genre>
    fun save(@NotBlank name: String): Genre
    fun deleteById(@NotNull id: Long): Boolean
    fun findAll(): List<Genre>
    fun update(@NotNull id: Long, @NotNull name: String): Boolean
}