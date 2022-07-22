package com.eliasnepo.motosport.application.review.find;

import com.eliasnepo.motosport.application.components.IAuthComponent;
import com.eliasnepo.motosport.application.review.find.dto.ReviewResponse;
import com.eliasnepo.motosport.domain.review.ReviewRepository;
import com.eliasnepo.motosport.infraestructure.review.jpa.ReviewEntity;
import com.eliasnepo.motosport.infraestructure.user.jpa.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class FindReviewsUseCase {

    private IAuthComponent authComponent;
    private ReviewRepository repository;

    public FindReviewsUseCase(IAuthComponent authComponent, ReviewRepository repository) {
        this.authComponent = authComponent;
        this.repository = repository;
    }

    public List<ReviewResponse> getMyReviews() {
        UserEntity userEntity = authComponent.authenticated();
        List<ReviewEntity> reviews = repository.findByUserId(userEntity.getId());

        return reviews.stream().map(review -> new ReviewResponse(review)).collect(Collectors.toList());
    }
}
