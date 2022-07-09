package com.eliasnepo.motosport.application.cars.create;

import com.eliasnepo.motosport.application.cars.create.dto.CreateCarRequest;
import com.eliasnepo.motosport.application.cars.create.dto.CreateCarResponse;
import com.eliasnepo.motosport.application.user.create.dto.UserRequest;
import com.eliasnepo.motosport.application.user.create.dto.UserResponse;
import com.eliasnepo.motosport.domain.cars.CarRepository;
import com.eliasnepo.motosport.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCarUseCase {

    private final CarRepository repository;

    public CreateCarUseCase(CarRepository repository) {
        this.repository = repository;
    }

    public CreateCarResponse createUser(CreateCarRequest request) {
        var carDomain = request.toModel();
        carDomain = repository.create(carDomain);
        return new CreateCarResponse(carDomain);
    }
}
