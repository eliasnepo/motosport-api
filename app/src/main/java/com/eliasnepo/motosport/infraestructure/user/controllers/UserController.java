package com.eliasnepo.motosport.infraestructure.user.controllers;

import com.eliasnepo.motosport.application.user.create.CreateUserUseCase;
import com.eliasnepo.motosport.application.user.create.dto.CreateUserRequest;
import com.eliasnepo.motosport.application.user.create.dto.CreateUserResponse;
import com.eliasnepo.motosport.infraestructure.user.jpa.UserRepositoryImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserRepositoryImpl repository;
    private final CreateUserUseCase createUserService;

    public UserController(final UserRepositoryImpl repository) {
        this.repository = repository;
        this.createUserService = new CreateUserUseCase(repository);
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
}
