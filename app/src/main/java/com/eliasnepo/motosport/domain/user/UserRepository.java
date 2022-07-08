package com.eliasnepo.motosport.domain.user;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {

    List<User> findAll();
    Optional<User> findById(Long id);
    User create(User userRequest);
    void delete(Long id);

}
