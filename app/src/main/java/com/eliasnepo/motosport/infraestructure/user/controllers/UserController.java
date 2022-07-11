package com.eliasnepo.motosport.infraestructure.user.controllers;

import com.eliasnepo.motosport.application.cars.find.dto.FindCarResponse;
import com.eliasnepo.motosport.application.user.create.CreateUserUseCase;
import com.eliasnepo.motosport.application.user.create.dto.CreateUserRequest;
import com.eliasnepo.motosport.application.user.create.dto.CreateUserResponse;
import com.eliasnepo.motosport.application.user.find.FindUserUseCase;
import com.eliasnepo.motosport.application.user.find.dto.FindUserResponse;
import com.eliasnepo.motosport.infraestructure.user.jpa.UserRepositoryImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserRepositoryImpl repository;
    private final CreateUserUseCase createUserService;
    private final FindUserUseCase findUserService;

    public UserController(final UserRepositoryImpl repository) {
        this.repository = repository;
        this.createUserService = new CreateUserUseCase(repository);
        this.findUserService = new FindUserUseCase(repository);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<CreateUserResponse> insertUser(@Valid @RequestBody CreateUserRequest userRequest) {
        CreateUserResponse userResponse = createUserService.createUser(userRequest);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userResponse.getId())
                .toUri();

        return ResponseEntity.created(uri).body(userResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FindUserResponse> findCarById(@PathVariable Long id) {
        FindUserResponse response = findUserService.findUserById(id);
        return ResponseEntity.ok(response);
    }
}
