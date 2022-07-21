package com.eliasnepo.motosport.infraestructure.review;

import com.eliasnepo.motosport.infraestructure.review.jpa.ReviewEntity;
import com.eliasnepo.motosport.infraestructure.review.jpa.ReviewRepositoryJpa;
import com.eliasnepo.motosport.infraestructure.user.jpa.UserEntity;
import com.eliasnepo.motosport.infraestructure.user.jpa.UserRepositoryJpa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class AssociateUserToReview {

    private final static Logger log = LoggerFactory.getLogger(AssociateUserToReview.class);

    @Autowired
    private ReviewRepositoryJpa reviewRepositoryJpa;
    @Autowired
    private UserRepositoryJpa userRepositoryJpa;

    @Scheduled(fixedDelayString = "5000")
    public void associateUserToReview() {
        List<ReviewEntity> reviewList = reviewRepositoryJpa.findAllWhereUserIsNull();

        if (reviewList.size() == 0) {
            return;
        }

        reviewList.forEach(review -> {
            UserEntity guesser = userRepositoryJpa.findRandomUser();
            review.setGuesser(guesser);
            reviewRepositoryJpa.save(review);
        });
        log.info("Added {} guessers to reviews", reviewList.size());
    }
}
