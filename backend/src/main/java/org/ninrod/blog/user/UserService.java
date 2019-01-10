package org.ninrod.blog.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    UserRepository repo;
    @Autowired
    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<Usuario> getUsers() {
        Iterable<Usuario> all = repo.findAll();
        List<Usuario> users = new ArrayList<>();
        all.forEach(users::add);
        return users;
    }
}
