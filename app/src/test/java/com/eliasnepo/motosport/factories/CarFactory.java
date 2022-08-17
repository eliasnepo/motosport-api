package com.eliasnepo.motosport.factories;

import com.eliasnepo.motosport.infraestructure.cars.jpa.CarEntity;
import com.eliasnepo.motosport.infraestructure.category.jpa.CategoryEntity;

import java.time.LocalDate;

public class CarFactory {

    public static CarEntity create(CategoryEntity category) {
        return new CarEntity(
                "Ferrari",
                "SF1000",
                13,
                645,
                5,
                "http:image",
                LocalDate.of(2020, 1, 1),
                category
        );
    }
}
