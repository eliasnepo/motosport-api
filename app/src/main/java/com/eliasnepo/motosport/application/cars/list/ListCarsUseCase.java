package com.eliasnepo.motosport.application.cars.list;

import com.eliasnepo.motosport.application.cars.list.dto.ListCarResponse;
import com.eliasnepo.motosport.domain.cars.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ListCarsUseCase {

    private final CarRepository repository;

    public ListCarsUseCase(CarRepository repository) {
        this.repository = repository;
    }

    public Page<ListCarResponse> listCars(Pageable pageable) {
        return repository.findAll(pageable).map(car -> new ListCarResponse(car));
    }
}
