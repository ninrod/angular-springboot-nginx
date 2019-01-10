package org.ninrod.blog

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<Usuario, String>