package com.example.wegoogift.repository;

import com.example.wegoogift.model.entity.CompanyEntity;
import com.example.wegoogift.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface CompaniesRepository extends CrudRepository<CompanyEntity, Long> {

    CompanyEntity findByUsersAndId(UserEntity userEntity, Long id);
}
