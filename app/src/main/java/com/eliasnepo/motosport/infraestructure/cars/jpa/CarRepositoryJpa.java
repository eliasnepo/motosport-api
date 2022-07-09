package com.eliasnepo.motosport.infraestructure.cars.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepositoryJpa extends JpaRepository<CarEntity, Long> {
}
