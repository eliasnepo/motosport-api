package com.eliasnepo.motosport.application.user.create;

import com.eliasnepo.motosport.application.user.create.dto.UserRequest;
import com.eliasnepo.motosport.application.user.create.dto.UserResponse;
import com.eliasnepo.motosport.domain.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserCreatedUseCase {

    private UserRepository userRepository;

    public UserCreatedUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserRequest request) {
        var userEntity = request.toModel();
        userEntity = userRepository.create(userEntity);
        return new UserResponse(userEntity.getId(), userEntity.getName(), userEntity.getEmail());
    }
}
