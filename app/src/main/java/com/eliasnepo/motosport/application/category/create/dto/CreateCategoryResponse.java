package com.eliasnepo.motosport.application.category.create.dto;

import com.eliasnepo.motosport.domain.category.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateCategoryResponse {

    private Long id;
    private String name;

    public CreateCategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
