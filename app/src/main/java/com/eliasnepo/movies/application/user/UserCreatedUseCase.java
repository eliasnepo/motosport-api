package com.eliasnepo.movies.application.user;

import com.eliasnepo.movies.domain.user.User;
import com.eliasnepo.movies.domain.user.UserRepository;

public class UserCreatedUseCase {

    private UserRepository userRepository;

    public UserCreatedUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserRequest request) {
        var userEntity = request.toModel();
        userRepository.create(userEntity);
        return new UserResponse(userEntity.getId(), userEntity.getName(), userEntity.getEmail());
    }
}
