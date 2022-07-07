package com.eliasnepo.motosport.domain.user;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "tb_user")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id = null;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public void changePassword(String newPassword) {
        this.setPassword(newPassword);
    }
}
