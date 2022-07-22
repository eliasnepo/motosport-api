package com.eliasnepo.motosport.infraestructure.review.controllers;

import com.eliasnepo.motosport.application.review.find.FindReviewsUseCase;
import com.eliasnepo.motosport.application.review.find.dto.ReviewResponse;
import com.eliasnepo.motosport.infraestructure.config.security.utils.AuthComponent;
import com.eliasnepo.motosport.infraestructure.review.jpa.ReviewRepositoryImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewController {

    private AuthComponent authComponent;
    private ReviewRepositoryImpl repository;
    private FindReviewsUseCase reviewService;

    public ReviewController(AuthComponent authComponent, ReviewRepositoryImpl repository) {
        this.authComponent = authComponent;
        this.repository = repository;
        this.reviewService = new FindReviewsUseCase(authComponent, repository);
    }

    @GetMapping(value = "/reviews")
    public ResponseEntity<List<ReviewResponse>> findMyReviews() {
        List<ReviewResponse> reviews = reviewService.getMyReviews();
        return ResponseEntity.ok(reviews);
    }
}
