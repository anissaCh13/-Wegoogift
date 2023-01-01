package com.example.wegoogift.repository;

import com.example.wegoogift.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
