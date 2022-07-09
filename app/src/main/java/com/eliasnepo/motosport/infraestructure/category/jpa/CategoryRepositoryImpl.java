package com.eliasnepo.motosport.infraestructure.category.jpa;

import com.eliasnepo.motosport.domain.category.Category;
import com.eliasnepo.motosport.domain.category.CategoryRepository;
import com.eliasnepo.motosport.infraestructure.cars.jpa.CarEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    @Autowired
    private CategoryRepositoryJpa categoryRepositoryJpa;

    @Override
    public List<Category> findAll() {
        return categoryRepositoryJpa.findAll().stream().map(CategoryEntity::toDomain).toList();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepositoryJpa.findById(id).map(CategoryEntity::toDomain);
    }

    @Override
    public Category create(Category category) {
        CategoryEntity categoryEntity = CategoryEntity.fromDomain(category);

        categoryEntity = categoryRepositoryJpa.save(categoryEntity);
        return categoryEntity.toDomain();
    }

    @Override
    public void delete(Long id) {
        categoryRepositoryJpa.deleteById(id);
    }
}
