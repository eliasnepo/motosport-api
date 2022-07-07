package com.eliasnepo.movies.domain.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> create(User userRequest);
    void delete(Long id);

}
