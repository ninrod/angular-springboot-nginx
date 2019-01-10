package org.ninrod.blog.user;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<Usuario, String> {
    List<Usuario> findByLastname(@Param("name") String name);
}
