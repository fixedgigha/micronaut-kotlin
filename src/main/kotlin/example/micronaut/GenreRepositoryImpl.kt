package example.micronaut

import example.micronaut.domain.Genre
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession
import io.micronaut.spring.tx.annotation.Transactional
import org.slf4j.LoggerFactory
import java.util.*
import javax.inject.Singleton
import javax.persistence.EntityManager

@Singleton
open class GenreRepositoryImpl(@CurrentSession val entityManager: EntityManager): GenreRepository {

    companion object {
        val logger = LoggerFactory.getLogger(GenreRepositoryImpl::class.java)
    }

    @Transactional(readOnly = true)
    override fun findById(id: Long): Optional<Genre> = Optional.ofNullable(
            entityManager.find(Genre::class.java, id)
    )

    @Transactional
    override fun save(name: String): Genre {
        val genre = Genre(0, name, emptySet())
        entityManager.persist(genre)
        return genre
    }

    @Transactional
    override fun deleteById(id: Long): Boolean {
        return findById(id).map {
            entityManager.remove(it)
            true
        }.orElse(false)
    }

    @Transactional(readOnly = true)
    override fun findAll(): List<Genre> {
        val query = entityManager.createQuery("SELECT g FROM Genre as g", Genre::class.java)
        return query.resultList
    }

    @Transactional
    override fun update(id: Long, name: String): Boolean {
        logger.info("Updating {} with name {}", id, name)
        return findById(id).map {
            it.name = name
            entityManager.persist(it)
            true
        }.orElse(false)
    }
}