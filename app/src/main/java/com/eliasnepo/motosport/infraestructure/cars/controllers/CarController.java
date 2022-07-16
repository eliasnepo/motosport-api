package com.eliasnepo.motosport.infraestructure.cars.controllers;

import com.eliasnepo.motosport.application.cars.create.CreateCarUseCase;
import com.eliasnepo.motosport.application.cars.create.FileStorage;
import com.eliasnepo.motosport.application.cars.create.dto.CreateCarRequest;
import com.eliasnepo.motosport.application.cars.create.dto.CreateCarResponse;
import com.eliasnepo.motosport.application.cars.find.FindCarUseCase;
import com.eliasnepo.motosport.application.cars.find.dto.FindCarResponse;
import com.eliasnepo.motosport.infraestructure.cars.jpa.CarRepositoryImpl;
import com.eliasnepo.motosport.infraestructure.category.jpa.CategoryRepositoryImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarRepositoryImpl repository;
    private final CategoryRepositoryImpl categoryRepository;
    private final CreateCarUseCase createCarService;
    private final FindCarUseCase findCarService;
    private final FileStorage fileStorage;

    public CarController(final CarRepositoryImpl repository, final CategoryRepositoryImpl categoryRepository, FileStorage fileStorage) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.fileStorage = fileStorage;
        this.createCarService = new CreateCarUseCase(repository, categoryRepository, fileStorage);
        this.findCarService = new FindCarUseCase(repository);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<CreateCarResponse> insertCar(@Valid @RequestPart("file") MultipartFile file, @RequestPart("data") CreateCarRequest request) {
        CreateCarResponse carResponse = createCarService.createCar(request, file);

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
