package com.eliasnepo.motosport.infraestructure.review.jpa;

import com.eliasnepo.motosport.domain.cars.Car;
import com.eliasnepo.motosport.domain.review.Review;
import com.eliasnepo.motosport.domain.user.User;
import com.eliasnepo.motosport.infraestructure.cars.jpa.CarEntity;
import com.eliasnepo.motosport.infraestructure.category.jpa.CategoryEntity;
import com.eliasnepo.motosport.infraestructure.user.jpa.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tb_review")
@NoArgsConstructor @AllArgsConstructor @Data
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne
    private UserEntity guesser;
    @ManyToOne
    private CarEntity car;
    private Boolean isRated = false;

    public ReviewEntity(String text, UserEntity guesser, CarEntity car, Boolean isRated) {
        this.text = text;
        this.guesser = guesser;
        this.car = car;
        this.isRated = isRated;
    }

    public Review toDomain() {
        return new Review(getId(), getText(), getGuesser().toDomain(), getCar().toDomain(), getIsRated());
    }

    public static ReviewEntity fromDomain(Review review) {
        return new ReviewEntity(review.getId(), review.getText(), UserEntity.fromDomain(review.getGuesser()), CarEntity.fromDomain(review.getCar()), review.getIsRated());
    }
}
