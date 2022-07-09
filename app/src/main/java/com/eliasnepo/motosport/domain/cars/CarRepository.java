package com.eliasnepo.motosport.domain.cars;

import com.eliasnepo.motosport.domain.category.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository {

    List<Car> findAll();
    Optional<Car> findById(Long id);
    Car create(Car userRequest);
    void delete(Long id);
}
