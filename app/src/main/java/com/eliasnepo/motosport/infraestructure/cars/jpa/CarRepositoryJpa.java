package com.eliasnepo.motosport.infraestructure.cars.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepositoryJpa extends JpaRepository<CarEntity, Long> {

    Page<CarEntity> findAll(Pageable pageable);
}
