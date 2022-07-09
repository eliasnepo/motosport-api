package com.eliasnepo.motosport.application.user.create;

import com.eliasnepo.motosport.application.user.create.dto.CreateUserRequest;
import com.eliasnepo.motosport.application.user.create.dto.CreateUserResponse;
import com.eliasnepo.motosport.domain.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {

    private UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CreateUserResponse createUser(CreateUserRequest request) {
        var userEntity = request.toModel();
        userEntity = userRepository.create(userEntity);
        return new CreateUserResponse(userEntity.getId(), userEntity.getName(), userEntity.getEmail());
    }
}
