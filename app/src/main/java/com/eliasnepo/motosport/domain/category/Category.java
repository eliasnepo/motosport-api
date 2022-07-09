package com.eliasnepo.motosport.domain.category;

import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    private Long id;
    private String name;

    public Category(String name) {
        this.name = name;
    }
}
