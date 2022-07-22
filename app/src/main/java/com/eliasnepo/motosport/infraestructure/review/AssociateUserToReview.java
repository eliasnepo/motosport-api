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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class AssociateUserToReview {

    private final static Logger log = LoggerFactory.getLogger(AssociateUserToReview.class);

    @Autowired
    private ReviewRepositoryJpa reviewRepositoryJpa;
    @Autowired
    private UserRepositoryJpa userRepositoryJpa;

    @Scheduled(fixedDelay = 300000)
    public void associateUserToReview() {
        List<ReviewEntity> reviewList = reviewRepositoryJpa.findAllWhereUserIsNull();

        if (reviewList.size() == 0) {
            return;
        }

        reviewList.forEach(review -> {
            Optional<UserEntity> guesser = userRepositoryJpa.findById(getRandomNumber());
            review.setGuesser(guesser.get());
            reviewRepositoryJpa.save(review);
        });
        log.info("Added {} guessers to reviews", reviewList.size());
    }

    private Long getRandomNumber() {
        return Long.valueOf(ThreadLocalRandom.current().nextInt(16, 25 + 1)); // generate a random userId between 16 and 25
    }
}
