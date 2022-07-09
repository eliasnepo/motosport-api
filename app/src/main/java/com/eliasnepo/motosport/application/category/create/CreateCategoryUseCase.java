package com.eliasnepo.motosport.application.category.create;

import com.eliasnepo.motosport.application.cars.create.dto.CreateCarRequest;
import com.eliasnepo.motosport.application.cars.create.dto.CreateCarResponse;
import com.eliasnepo.motosport.application.category.create.dto.CreateCategoryRequest;
import com.eliasnepo.motosport.application.category.create.dto.CreateCategoryResponse;
import com.eliasnepo.motosport.domain.cars.CarRepository;
import com.eliasnepo.motosport.domain.category.Category;
import com.eliasnepo.motosport.domain.category.CategoryRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class CreateCategoryUseCase {

    private final CategoryRepository repository;

    public CreateCategoryUseCase(CategoryRepository repository) {
        this.repository = repository;
    }

    public CreateCategoryResponse createCar(CreateCategoryRequest request) {
        var categoryDomain = request.toModel();
        categoryDomain = repository.create(categoryDomain);
        return new CreateCategoryResponse(categoryDomain);
    }
}
