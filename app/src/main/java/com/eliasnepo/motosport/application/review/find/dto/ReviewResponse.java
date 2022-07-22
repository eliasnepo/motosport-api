package com.eliasnepo.motosport.application.review.find.dto;

import com.eliasnepo.motosport.infraestructure.review.jpa.ReviewEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReviewResponse {
    private Long id;
    private String text;
    private Boolean isRated = false;

    public ReviewResponse(ReviewEntity review) {
        this.id = review.getId();
        this.text = review.getText();
        this.isRated = review.getIsRated();
    }
}
