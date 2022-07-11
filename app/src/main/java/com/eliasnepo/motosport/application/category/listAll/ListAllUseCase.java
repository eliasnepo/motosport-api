package com.eliasnepo.motosport.application.category.listAll;

import com.eliasnepo.motosport.application.cars.find.dto.FindCarResponse;
import com.eliasnepo.motosport.domain.cars.Car;
import com.eliasnepo.motosport.domain.cars.CarRepository;
import com.eliasnepo.motosport.domain.category.Category;
import com.eliasnepo.motosport.domain.category.CategoryRepository;

import java.util.List;
import java.util.Optional;

public class ListAllUseCase {

    private final CategoryRepository repository;

    public ListAllUseCase(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> listAll() {
        return repository.findAll();
    }
}
