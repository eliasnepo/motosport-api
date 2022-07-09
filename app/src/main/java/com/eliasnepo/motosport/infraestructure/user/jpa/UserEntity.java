package com.eliasnepo.motosport.infraestructure.user.jpa;

import com.eliasnepo.motosport.domain.user.User;
import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();

    public UserEntity(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User toDomain() {
        return new User(getId(), getName(), getEmail(), getPassword(), getRoles().stream().map(RoleEntity::toDomain).collect(Collectors.toSet()));
    }

    public static UserEntity fromDomain(User user) {
        return new UserEntity(user.getName(), user.getEmail(), user.getPassword());
    }
}
