package com.eliasnepo.motosport.infraestructure.config.security.utils;

import com.eliasnepo.motosport.application.components.IAuthComponent;
import com.eliasnepo.motosport.infraestructure.user.jpa.UserEntity;
import com.eliasnepo.motosport.infraestructure.user.jpa.UserRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AuthComponent implements IAuthComponent {

    @Autowired
    private UserRepositoryJpa userRepository;

    @Transactional(readOnly = true)
    public UserEntity authenticated() {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            return userRepository.findByEmail(email).get();
        }
        catch (Exception e) {
            throw new RuntimeException("Invalid user");
        }
    }
}