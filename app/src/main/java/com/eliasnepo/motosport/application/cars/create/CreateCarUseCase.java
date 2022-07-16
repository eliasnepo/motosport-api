package com.eliasnepo.motosport.application.cars.create;

import com.eliasnepo.motosport.application.cars.create.dto.CreateCarRequest;
import com.eliasnepo.motosport.application.cars.create.dto.CreateCarResponse;
import com.eliasnepo.motosport.domain.cars.CarRepository;
import com.eliasnepo.motosport.domain.category.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

public class CreateCarUseCase {

    private final CarRepository repository;
    private final CategoryRepository categoryRepository;
    private final FileStorage fileStorage;

    public CreateCarUseCase(CarRepository repository, CategoryRepository categoryRepository, FileStorage fileStorage) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.fileStorage = fileStorage;
    }

    public CreateCarResponse createCar(CreateCarRequest request, MultipartFile file) {
        URL imgUrl = fileStorage.uploadFile(file);
        var carDomain = request.toModel(categoryRepository, imgUrl);
        carDomain = repository.create(carDomain);
        return new CreateCarResponse(carDomain);
    }
}
