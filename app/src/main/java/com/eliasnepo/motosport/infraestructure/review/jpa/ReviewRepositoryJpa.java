package com.eliasnepo.motosport.infraestructure.review.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepositoryJpa extends JpaRepository<ReviewEntity, Long> {
    @Query(value = "SELECT r FROM ReviewEntity r WHERE r.guesser IS NULL")
    List<ReviewEntity> findAllWhereUserIsNull();

    List<ReviewEntity> findByGuesserId(Long id);
}
