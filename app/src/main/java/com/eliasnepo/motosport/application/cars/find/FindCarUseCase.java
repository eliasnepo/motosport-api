package com.eliasnepo.motosport.application.cars.find;

import com.eliasnepo.motosport.application.cars.create.dto.CreateCarResponse;
import com.eliasnepo.motosport.application.cars.find.dto.FindCarResponse;
import com.eliasnepo.motosport.domain.cars.Car;
import com.eliasnepo.motosport.domain.cars.CarRepository;
import com.eliasnepo.motosport.domain.category.CategoryRepository;

import java.util.Optional;

public class FindCarUseCase {

    private final CarRepository repository;

    public FindCarUseCase(CarRepository repository) {
        this.repository = repository;
    }

    public FindCarResponse findCarById(Long id) {
        Optional<Car> car = repository.findById(id);

        if (car.isEmpty()) {
            throw new RuntimeException("Car does not exists with ID: " + id);
        }

        return new FindCarResponse(car.get());
    }
}
