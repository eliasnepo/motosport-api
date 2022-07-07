package com.eliasnepo.motosport.infraestructure.user.jpa;

import com.eliasnepo.motosport.domain.user.User;
import com.eliasnepo.motosport.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    UserRepositoryJpa userRepositoryJpa;

    @Override
    public List<User> findAll() {
        return userRepositoryJpa.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepositoryJpa.findById(id);
    }

    @Override
    public Optional<User> create(User userRequest) {
        return Optional.of(userRepositoryJpa.save(userRequest));
    }

    @Override
    public void delete(Long id) {
        userRepositoryJpa.deleteById(id);
    }
}
