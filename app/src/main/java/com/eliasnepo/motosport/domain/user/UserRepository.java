package com.eliasnepo.motosport.domain.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Page<User> findAll(Pageable pageable);
    Optional<User> findById(Long id);
    User create(User userRequest);
    void delete(Long id);

}
