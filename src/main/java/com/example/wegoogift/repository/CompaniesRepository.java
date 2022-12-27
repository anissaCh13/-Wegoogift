package com.example.wegoogift.repository;

import com.example.wegoogift.entity.CompanyEntity;
import org.springframework.data.repository.CrudRepository;

public interface CompaniesRepository extends CrudRepository<CompanyEntity, Long> {
}
