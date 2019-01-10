package org.ninrod.blog

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit.jupiter.SpringExtension

@DataJpaTest
@ExtendWith(SpringExtension::class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositoriesTests(@Autowired val entityManager: TestEntityManager,
                        @Autowired val userRepository: UserRepository) {

    @Test
    fun `findall test`() {
        val juergen = Usuario("springjuergen", "Juergen", "Hoeller")
        entityManager.persist(juergen)
        val found = userRepository.findAll()
        assertThat(found.count()).isEqualTo(1)
    }

    @Test
    fun `When findById then return User`() {
        val juergen = Usuario("springjuergen", "Juergen", "Hoeller")
        entityManager.persist(juergen)
        entityManager.flush()
        val found = userRepository.findById(juergen.login)
        assertThat(found.get()).isEqualTo(juergen)
    }
}
