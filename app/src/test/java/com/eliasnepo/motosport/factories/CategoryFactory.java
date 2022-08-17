package com.eliasnepo.motosport.factories;

import com.eliasnepo.motosport.infraestructure.cars.jpa.CarEntity;
import com.eliasnepo.motosport.infraestructure.category.jpa.CategoryEntity;

import java.time.LocalDate;

public class CategoryFactory {

    public static CategoryEntity create() {
        return new CategoryEntity("Formula 1");
    }
}
