package com.eliasnepo.motosport.application.category.create.dto;

import com.eliasnepo.motosport.domain.category.Category;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateCategoryRequest {

    @NotBlank
    private String name;

    @JsonCreator
    public CreateCategoryRequest(String name) {
        this.name = name;
    }

    public Category toModel() {
        return new Category(getName());
    }
}
