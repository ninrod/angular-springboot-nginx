package org.ninrod.blog;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<Usuario, String> {
    List<Usuario> findByLastname(@Param("name") String name);
}
