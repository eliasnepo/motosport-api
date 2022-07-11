package com.eliasnepo.motosport.infraestructure.cars.controllers;

import com.eliasnepo.motosport.application.cars.create.CreateCarUseCase;
import com.eliasnepo.motosport.application.cars.create.dto.CreateCarRequest;
import com.eliasnepo.motosport.application.cars.create.dto.CreateCarResponse;
import com.eliasnepo.motosport.application.cars.find.FindCarUseCase;
import com.eliasnepo.motosport.application.cars.find.dto.FindCarResponse;
import com.eliasnepo.motosport.infraestructure.cars.jpa.CarRepositoryImpl;
import com.eliasnepo.motosport.infraestructure.category.jpa.CategoryRepositoryImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarRepositoryImpl repository;
    private final CategoryRepositoryImpl categoryRepository;
    private final CreateCarUseCase createCarService;
    private final FindCarUseCase findCarService;

    public CarController(final CarRepositoryImpl repository, final CategoryRepositoryImpl categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.createCarService = new CreateCarUseCase(repository, categoryRepository);
        this.findCarService = new FindCarUseCase(repository);
    }

    @PostMapping
    public ResponseEntity<CreateCarResponse> insertCar(@Valid @RequestBody CreateCarRequest request) {
        CreateCarResponse carResponse = createCarService.createCar(request);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(carResponse.getId())
                .toUri();

        return ResponseEntity.created(uri).body(carResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FindCarResponse> findCarById(@PathVariable Long id) {
        FindCarResponse response = findCarService.findCarById(id);
        return ResponseEntity.ok(response);
    }
}
