package com.eliasnepo.motosport.application.user.find.dto;

import com.eliasnepo.motosport.domain.cars.Car;
import com.eliasnepo.motosport.domain.category.Category;
import com.eliasnepo.motosport.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class FindUserResponse {

    private Long id;
    private String name;
    private String email;

    public FindUserResponse(User domain) {
        this.id = domain.getId();
        this.name = domain.getName();
        this.email = domain.getEmail();
    }
}
