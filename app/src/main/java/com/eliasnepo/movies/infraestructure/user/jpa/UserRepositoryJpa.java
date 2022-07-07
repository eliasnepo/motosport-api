package com.eliasnepo.movies.infraestructure.user.jpa;

import com.eliasnepo.movies.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryJpa extends JpaRepository<User, Long> {
}
