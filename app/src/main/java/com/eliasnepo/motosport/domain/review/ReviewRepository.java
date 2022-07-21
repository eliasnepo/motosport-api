package com.eliasnepo.motosport.domain.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ReviewRepository {

    Page<Review> findAll(Pageable pageable);
    Optional<Review> findById(Long id);
    Review create(Review review);
}
