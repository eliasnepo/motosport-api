package com.eliasnepo.movies.application.user;

import com.eliasnepo.movies.domain.user.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String name;
    private String email;
}
