package com.eliasnepo.motosport.application.cars.create.dto;

import com.eliasnepo.motosport.domain.cars.Car;
import com.eliasnepo.motosport.domain.cars.CarRepository;
import com.eliasnepo.motosport.domain.category.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CreateCarRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String engine;
    @NotNull
    private Integer tyreSize;
    @NotNull
    private Integer weight;
    @NotNull
    private Integer championshipStanding;
    @NotNull @PastOrPresent
    private LocalDate year;
    @NotNull
    private Long categoryId;

    public Car toModel(CategoryRepository categoryRepository) {
        var category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) { throw new RuntimeException("Category does not exists."); }

        return new Car(this.name, this.engine, this.tyreSize, this.weight, this.championshipStanding, this.year, category.get());
    }
}
