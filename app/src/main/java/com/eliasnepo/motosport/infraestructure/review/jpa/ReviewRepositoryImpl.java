package com.eliasnepo.motosport.infraestructure.review.jpa;

import com.eliasnepo.motosport.domain.review.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepositoryImpl implements ReviewRepository {

    @Autowired
    private ReviewRepositoryJpa repository;

    @Override
    public List<ReviewEntity> findByUserId(Long id) {
        return repository.findByGuesserId(id);
    }
}
