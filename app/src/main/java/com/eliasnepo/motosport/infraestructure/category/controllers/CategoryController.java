package com.eliasnepo.motosport.infraestructure.category.controllers;

import com.eliasnepo.motosport.application.cars.create.CreateCarUseCase;
import com.eliasnepo.motosport.application.cars.create.dto.CreateCarRequest;
import com.eliasnepo.motosport.application.cars.create.dto.CreateCarResponse;
import com.eliasnepo.motosport.application.category.create.CreateCategoryUseCase;
import com.eliasnepo.motosport.application.category.create.dto.CreateCategoryRequest;
import com.eliasnepo.motosport.application.category.create.dto.CreateCategoryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CreateCategoryUseCase createCategoryService;

    public CategoryController(final CreateCategoryUseCase createCategoryService) {
        this.createCategoryService = createCategoryService;
    }

    @PostMapping
    public ResponseEntity<CreateCategoryResponse> insertCategory(@Valid @RequestBody CreateCategoryRequest request) {
        CreateCategoryResponse categoryResponse = createCategoryService.createCar(request);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoryResponse.getId())
                .toUri();

        return ResponseEntity.created(uri).body(categoryResponse);
    }
}
