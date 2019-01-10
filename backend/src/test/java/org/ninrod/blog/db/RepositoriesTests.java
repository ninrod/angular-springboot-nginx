package org.ninrod.blog.db;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.ninrod.blog.user.UserRepository;
import org.ninrod.blog.user.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;

import static org.springframework.test.util.AssertionErrors.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositoriesTests {
    @Autowired private TestEntityManager entityManager;
    @Autowired private UserRepository repository;

    @Test
    public void findallTest() {
        Usuario juergen = new Usuario("springjuergen", "Juergen", "Hoeller", "blah");
        entityManager.persist(juergen);
        Iterable<Usuario> found = repository.findAll();
        List<Usuario> usuarios = new ArrayList<>();
        found.forEach(usuarios::add);
        assertEquals("there should be at least one user", true, usuarios.size() > 0);
    }

    @Test
    public void whenFindByIdThenReturnUser() {
        Usuario juergen = new Usuario("springjuergen", "Juergen", "Hoeller", "blah");
        entityManager.persist(juergen);
        entityManager.flush();
        Usuario found = repository.findById(juergen.getLogin()).get();
        assertThat("found should be equal to juergen", found.equals(juergen));
    }
}
