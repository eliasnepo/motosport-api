package com.eliasnepo.motosport.infraestructure.cars.jpa;

import com.eliasnepo.motosport.domain.cars.Car;
import com.eliasnepo.motosport.domain.user.User;
import com.eliasnepo.motosport.infraestructure.category.jpa.CategoryEntity;
import com.eliasnepo.motosport.infraestructure.user.jpa.UserEntity;
import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "car_year", nullable = false)
    private LocalDate year;

    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    public CarEntity(String name, String engine, Integer tyreSize, Integer weight, Integer championshipStanding, String imgUrl, LocalDate year, CategoryEntity category) {
        this.name = name;
        this.engine = engine;
        this.tyreSize = tyreSize;
        this.weight = weight;
        this.championshipStanding = championshipStanding;
        this.imgUrl = imgUrl;
        this.year = year;
        this.category = category;
    }

    public Car toDomain() {
        return new Car(getId(), getName(), getEngine(), getTyreSize(), getWeight(), getChampionshipStanding(), getYear(), getImgUrl(), getCategory().toDomain());
    }

    public static CarEntity fromDomain(Car car) {
        return new CarEntity(car.getId(), car.getName(), car.getEngine(), car.getTyreSize(), car.getWeight(), car.getChampionshipStanding(), car.getYear(), car.getImgUrl(), CategoryEntity.fromDomain(car.getCategory()));
    }
}
