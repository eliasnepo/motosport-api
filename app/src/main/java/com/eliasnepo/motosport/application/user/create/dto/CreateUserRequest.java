package com.eliasnepo.motosport.application.user.create.dto;

import com.eliasnepo.motosport.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.*;

@AllArgsConstructor
@Getter
public class CreateUserRequest {
    @NotBlank @Size(max = 50)
    private String name;
    @NotBlank @Email
    private String email;
    @NotBlank @Size(min = 8, max = 40)
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public User toModel() {
        return new User(this.name, this.email, this.password);
    }
}
