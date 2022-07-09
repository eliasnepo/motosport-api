package com.eliasnepo.motosport.infraestructure.cars.controllers;

import com.eliasnepo.motosport.application.cars.create.CreateCarUseCase;
import com.eliasnepo.motosport.application.cars.create.dto.CreateCarRequest;
import com.eliasnepo.motosport.application.cars.create.dto.CreateCarResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CreateCarUseCase createCarService;

    public CarController(final CreateCarUseCase createCarService) {
        this.createCarService = createCarService;
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
}
