package com.eliasnepo.motosport.domain.review;

import com.eliasnepo.motosport.infraestructure.review.jpa.ReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {

    List<ReviewEntity> findByUserId(Long id);
}
