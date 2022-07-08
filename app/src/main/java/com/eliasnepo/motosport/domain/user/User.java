package com.eliasnepo.motosport.domain.user;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private String email;
    private String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void changePassword(String newPassword) {
        this.setPassword(newPassword);
    }
}
