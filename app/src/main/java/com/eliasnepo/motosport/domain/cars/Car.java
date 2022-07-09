package com.eliasnepo.motosport.domain.cars;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private Long id;
    private String name;
    private String engine;
    private Integer tyreSize;
    private Integer weight;
    private Integer championshipStanding;
    private LocalDate year;

    public Car(String name, String engine, Integer tyreSize, Integer weight, Integer championshipStanding, LocalDate year) {
        this.name = name;
        this.engine = engine;
        this.tyreSize = tyreSize;
        this.weight = weight;
        this.championshipStanding = championshipStanding;
        this.year = year;
    }

    public void changeCurrentStanding(Integer currentStanding) {
        if (this.year.getYear() < LocalDate.now().getYear()) {
            throw new RuntimeException("You can't change the car's standings from another year.");
        }
        this.setChampionshipStanding(currentStanding);
    }
}
