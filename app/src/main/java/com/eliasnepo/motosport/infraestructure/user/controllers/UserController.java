package com.eliasnepo.motosport.infraestructure.user.controllers;

import com.eliasnepo.motosport.application.user.create.CreateUserUseCase;
import com.eliasnepo.motosport.application.user.create.dto.CreateUserRequest;
import com.eliasnepo.motosport.application.user.create.dto.CreateUserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final CreateUserUseCase createUserService;

    public UserController(final CreateUserUseCase createUserService) {
        this.createUserService = createUserService;
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
