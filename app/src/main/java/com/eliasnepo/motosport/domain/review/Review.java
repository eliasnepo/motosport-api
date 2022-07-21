package com.eliasnepo.motosport.domain.review;

import com.eliasnepo.motosport.domain.cars.Car;
import com.eliasnepo.motosport.domain.user.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    private Long id;
    private String text;
    private User guesser;
    private Car car;
    private Boolean isRated = false;

}
