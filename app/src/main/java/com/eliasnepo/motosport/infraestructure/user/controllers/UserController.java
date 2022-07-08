package com.eliasnepo.motosport.infraestructure.user.controllers;

import com.eliasnepo.motosport.application.user.create.UserCreatedUseCase;
import com.eliasnepo.motosport.application.user.create.dto.UserRequest;
import com.eliasnepo.motosport.application.user.create.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "user/register")
public class UserController {

    private final UserCreatedUseCase createUserService;

    public UserController(final UserCreatedUseCase createUserService) {
        this.createUserService = createUserService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> insertUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = createUserService.createUser(userRequest);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userResponse.getId())
                .toUri();

        return ResponseEntity.created(uri).body(userResponse);
    }
}
