package com.eliasnepo.motosport.domain.user;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private String email;
    private String password;

    private Set<Role> roles = new HashSet<>();

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void changePassword(String newPassword) {
        this.setPassword(newPassword);
    }
}
