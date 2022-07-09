package com.eliasnepo.motosport.application.cars.create.dto;

import com.eliasnepo.motosport.domain.cars.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CreateCarRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String engine;
    @NotBlank
    private Integer tyreSize;
    @NotBlank
    private Integer weight;
    @NotBlank
    private Integer championshipStanding;
    @NotBlank @PastOrPresent
    private LocalDate year;

    public Car toModel() {
        return new Car(this.name, this.engine, this.tyreSize, this.weight, this.championshipStanding, this.year);
    }
}
