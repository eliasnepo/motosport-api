package com.eliasnepo.motosport.application.cars.list.dto;

import com.eliasnepo.motosport.domain.cars.Car;
import com.eliasnepo.motosport.domain.category.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class ListCarResponse {

    private Long id;
    private String name;
    private String engine;
    private Integer tyreSize;
    private Integer weight;
    private Integer championshipStanding;
    private LocalDate year;
    private String imgUrl;
    private Category category;

    public ListCarResponse(Car domain) {
        this.id = domain.getId();
        this.name = domain.getName();
        this.engine = domain.getEngine();
        this.tyreSize = domain.getTyreSize();
        this.weight = domain.getWeight();
        this.championshipStanding = domain.getChampionshipStanding();
        this.year = domain.getYear();
        this.imgUrl = domain.getImgUrl();
        this.category = domain.getCategory();
    }
}