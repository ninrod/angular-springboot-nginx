package org.ninrod.blog

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PhraseService {
    fun phrase(): String = "Hello, "
}

@Service
class UserService(@Autowired val repo: UserRepository) {
    fun getUsers(): List<Usuario> = repo.findAll().toList()
}
