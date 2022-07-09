package com.eliasnepo.motosport.infraestructure.cars.jpa;

import com.eliasnepo.motosport.domain.cars.Car;
import com.eliasnepo.motosport.domain.user.User;
import com.eliasnepo.motosport.infraestructure.user.jpa.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_car")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String engine;
    @Column(nullable = false)
    private Integer tyreSize;
    @Column(nullable = false)
    private Integer weight;
    @Column(nullable = true)
    private Integer championshipStanding;
    @Column(nullable = false)
    private LocalDate year;

    public CarEntity(String name, String engine, Integer tyreSize, Integer weight, Integer championshipStanding, LocalDate year) {
        this.name = name;
        this.engine = engine;
        this.tyreSize = tyreSize;
        this.weight = weight;
        this.championshipStanding = championshipStanding;
        this.year = year;
    }

    public Car toDomain() {
        return new Car(getId(), getName(), getEngine(), getTyreSize(), getWeight(), getChampionshipStanding(), getYear());
    }

    public static CarEntity fromDomain(Car car) {
        return new CarEntity(car.getId(), car.getName(), car.getEngine(), car.getTyreSize(), car.getWeight(), car.getChampionshipStanding(), car.getYear());
    }
}
