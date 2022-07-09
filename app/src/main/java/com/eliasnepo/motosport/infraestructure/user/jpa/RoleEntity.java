package com.eliasnepo.motosport.infraestructure.user.jpa;

import com.eliasnepo.motosport.domain.user.Role;
import com.eliasnepo.motosport.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tb_role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;

    public Role toDomain() {
        return new Role(getId(), getAuthority());
    }
}
