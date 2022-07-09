package com.eliasnepo.motosport.application.cars.create;

import com.eliasnepo.motosport.application.cars.create.dto.CreateCarRequest;
import com.eliasnepo.motosport.application.cars.create.dto.CreateCarResponse;
import com.eliasnepo.motosport.domain.cars.CarRepository;
import com.eliasnepo.motosport.domain.category.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCarUseCase {

    private final CarRepository repository;
    private final CategoryRepository categoryRepository;

    public CreateCarUseCase(CarRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    public CreateCarResponse createCar(CreateCarRequest request) {
        var carDomain = request.toModel(categoryRepository);
        carDomain = repository.create(carDomain);
        return new CreateCarResponse(carDomain);
    }
}
