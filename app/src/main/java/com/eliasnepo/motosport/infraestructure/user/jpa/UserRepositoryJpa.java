package com.eliasnepo.motosport.infraestructure.user.jpa;

import com.eliasnepo.motosport.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryJpa extends JpaRepository<User, Long> {
}
