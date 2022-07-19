package com.eliasnepo.motosport.infraestructure.cars.jpa;

import com.eliasnepo.motosport.domain.cars.Car;
import com.eliasnepo.motosport.domain.cars.CarRepository;
import com.eliasnepo.motosport.domain.user.User;
import com.eliasnepo.motosport.infraestructure.user.jpa.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CarRepositoryImpl implements CarRepository {

    @Autowired
    private CarRepositoryJpa carRepositoryJpa;

    @Override
    public Page<Car> findAll(Pageable pageable) {
        return carRepositoryJpa.findAll(pageable).map(CarEntity::toDomain);
    }

    @Override
    public Optional<Car> findById(Long id) {
        return carRepositoryJpa.findById(id).map(CarEntity::toDomain);
    }

    @Override
    public Car create(Car car) {
        CarEntity carEntity = CarEntity.fromDomain(car);

        carEntity = carRepositoryJpa.save(carEntity);
        return carEntity.toDomain();
    }

    @Override
    public void delete(Long id) {
        carRepositoryJpa.deleteById(id);
    }
}
