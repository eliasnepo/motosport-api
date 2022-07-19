package com.eliasnepo.motosport.infraestructure.user.jpa;

import com.eliasnepo.motosport.domain.user.User;
import com.eliasnepo.motosport.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    UserRepositoryJpa userRepositoryJpa;

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepositoryJpa.findAll(pageable).map(UserEntity::toDomain);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepositoryJpa.findById(id).map(UserEntity::toDomain);
    }

    @Override
    public User create(User user) {
        UserEntity userEntity = UserEntity.fromDomain(user);

        userEntity = userRepositoryJpa.save(userEntity);
        System.out.println(userEntity);
        return userEntity.toDomain();
    }

    @Override
    public void delete(Long id) {
        userRepositoryJpa.deleteById(id);
    }
}
