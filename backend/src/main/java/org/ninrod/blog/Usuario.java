package org.ninrod.blog;

import javax.persistence.Entity;

@Entity
public class Usuario {
    private String login;
    private String firstname;
    private String lastname;
    private String description;

    public Usuario() {}

    public Usuario(
            String login,
            String firstname,
            String lastname,
            String description
    ) {
        this.login = login;
        this.firstname = firstname;
        this.lastname = lastname;
        this.description = description;
    }

    public String getLogin() {
        return this.login;
    }
}
