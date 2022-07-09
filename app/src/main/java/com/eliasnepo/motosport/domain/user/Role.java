package com.eliasnepo.motosport.domain.user;

import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    private Long id;
    private String authority;
}
