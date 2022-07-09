package com.eliasnepo.motosport.application.cars.create.dto;

import com.eliasnepo.motosport.domain.cars.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class CreateCarResponse {

    private Long id;
    private String name;
    private String engine;
    private Integer tyreSize;
    private Integer weight;
    private Integer championshipStanding;
    private LocalDate year;

    public CreateCarResponse(Car carDomain) {
        this.id = carDomain.getId();
        this.name = carDomain.getName();
        this.engine = carDomain.getEngine();
        this.tyreSize = carDomain.getTyreSize();
        this.weight = carDomain.getWeight();
        this.championshipStanding = carDomain.getChampionshipStanding();
        this.year = carDomain.getYear();
    }
}
