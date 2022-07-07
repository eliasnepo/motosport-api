package com.eliasnepo.motosport.application.user;

import com.eliasnepo.motosport.domain.user.User;
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
