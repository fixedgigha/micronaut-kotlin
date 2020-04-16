package example.micronaut

import example.micronaut.domain.Genre
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.netty.handler.codec.http.HttpObject
import java.net.URI
import javax.validation.Valid

@Controller("/genres")
open class GenreController(val genreRepository: GenreRepository) {

    @Get("/{id}")
    fun getOne(id: Long): Genre? =
        genreRepository.findById(id).orElse(null)

    @Post("/")
    open fun create(@Body @Valid body: GenreCreateBody): HttpResponse<Genre> {
        val genre = genreRepository.save(body.name)

        return HttpResponse.created(genre).headers { headers ->
            headers.location(  URI.create("/genres/${genre.id}") )
        }
    }

    @Get("/")
    fun getAll(): List<Genre> = genreRepository.findAll()

    @Delete("/{id}")
    fun delete(id: Long): HttpResponse<Any> =
        if (genreRepository.deleteById(id))
            HttpResponse.ok<Any>()
        else
            HttpResponse.notFound()

    @Put("/{id}")
    fun update(id: Long, @Body body: GenreCreateBody): HttpResponse<Any> =
        if (genreRepository.update(id, body.name))
            HttpResponse.ok<Any>()
        else
            HttpResponse.notFound()
}