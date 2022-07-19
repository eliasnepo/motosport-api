package com.eliasnepo.motosport.domain.cars;

import com.eliasnepo.motosport.domain.category.Category;
import com.eliasnepo.motosport.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface CarRepository {

    Page<Car> findAll(Pageable pageable);
    Optional<Car> findById(Long id);
    Car create(Car userRequest);
    void delete(Long id);
}
