package com.eliasnepo.motosport.infraestructure.category.controllers;

import com.eliasnepo.motosport.application.cars.create.CreateCarUseCase;
import com.eliasnepo.motosport.application.cars.create.dto.CreateCarRequest;
import com.eliasnepo.motosport.application.cars.create.dto.CreateCarResponse;
import com.eliasnepo.motosport.application.category.create.CreateCategoryUseCase;
import com.eliasnepo.motosport.application.category.create.dto.CreateCategoryRequest;
import com.eliasnepo.motosport.application.category.create.dto.CreateCategoryResponse;
import com.eliasnepo.motosport.application.category.listAll.ListAllUseCase;
import com.eliasnepo.motosport.domain.category.Category;
import com.eliasnepo.motosport.infraestructure.category.jpa.CategoryRepositoryImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepositoryImpl categoryRepository;
    private final CreateCategoryUseCase createCategoryService;
    private final ListAllUseCase listAllService;

    public CategoryController(final CategoryRepositoryImpl categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.createCategoryService = new CreateCategoryUseCase(categoryRepository);
        this.listAllService = new ListAllUseCase(categoryRepository);
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

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> categories = listAllService.listAll();

        return ResponseEntity.ok(categories);
    }
}
