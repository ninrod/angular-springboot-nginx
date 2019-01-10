package org.ninrod.blog.user;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usuario {
    @Id
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

    public String getFirstname() {
        return this.firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public String getDescription() {
        return this.description;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setFirstname(String first) {
        this.firstname = first;
    }

    public void setLastname(String last) {
        this.lastname = last;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
