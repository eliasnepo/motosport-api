package com.eliasnepo.motosport.infraestructure.category.jpa;

import com.eliasnepo.motosport.domain.cars.Car;
import com.eliasnepo.motosport.domain.category.Category;
import com.eliasnepo.motosport.infraestructure.cars.jpa.CarEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tb_category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;

    public Category toDomain() {
        return new Category(getId(), getName());
    }

    public static CategoryEntity fromDomain(Category category) {
        return new CategoryEntity(category.getId(), category.getName());
    }
}
