package com.eliasnepo.motosport.application.cars.create;

import com.eliasnepo.motosport.application.cars.create.dto.CreateCarRequest;
import com.eliasnepo.motosport.application.cars.create.dto.CreateCarResponse;
import com.eliasnepo.motosport.domain.cars.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCarUseCase {

    private final CarRepository repository;

    public CreateCarUseCase(CarRepository repository) {
        this.repository = repository;
    }

    public CreateCarResponse createCar(CreateCarRequest request) {
        var carDomain = request.toModel();
        carDomain = repository.create(carDomain);
        return new CreateCarResponse(carDomain);
    }
}
