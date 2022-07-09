package com.eliasnepo.motosport.domain.category;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    List<Category> findAll();
    Optional<Category> findById(Long id);
    Category create(Category category);
    void delete(Long id);
}
