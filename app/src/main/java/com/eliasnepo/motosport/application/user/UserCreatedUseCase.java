package com.eliasnepo.motosport.application.user;

import com.eliasnepo.motosport.domain.user.UserRepository;

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
