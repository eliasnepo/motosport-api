package com.eliasnepo.motosport.application.user.find;

import com.eliasnepo.motosport.application.user.find.dto.FindUserResponse;
import com.eliasnepo.motosport.domain.cars.Car;
import com.eliasnepo.motosport.domain.cars.CarRepository;
import com.eliasnepo.motosport.domain.user.User;
import com.eliasnepo.motosport.domain.user.UserRepository;

import java.util.Optional;

public class FindUserUseCase {

    private final UserRepository repository;

    public FindUserUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public FindUserResponse findUserById(Long id) {
        Optional<User> user = repository.findById(id);

        if (user.isEmpty()) {
            throw new RuntimeException("User does not exists with ID: " + id);
        }

        return new FindUserResponse(user.get());
    }
}
