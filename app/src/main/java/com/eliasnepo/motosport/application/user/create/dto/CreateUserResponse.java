package com.eliasnepo.motosport.application.user.create.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateUserResponse {

    private Long id;
    private String name;
    private String email;
}
