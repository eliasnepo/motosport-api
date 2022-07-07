package com.eliasnepo.movies.application.user;

import com.eliasnepo.movies.domain.user.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserRequest {

    private String name;
    private String email;
    private String password;

    public User toModel() {
        return new User(name, email, password);
    }
}
