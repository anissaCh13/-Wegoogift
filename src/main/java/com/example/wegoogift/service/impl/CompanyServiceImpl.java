package com.example.wegoogift.service.impl;

import com.example.wegoogift.mapper.CompanyMapper;
import com.example.wegoogift.model.dto.CompanyDTO;
import com.example.wegoogift.model.entity.CompanyEntity;
import com.example.wegoogift.repository.CompaniesRepository;
import com.example.wegoogift.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    CompaniesRepository companiesRepository;

    CompanyMapper companyMapper;

    public CompanyServiceImpl(CompaniesRepository companiesRepository, CompanyMapper companyMapper) {
        this.companiesRepository = companiesRepository;
        this.companyMapper = companyMapper;
    }

    @Override
    public List<CompanyDTO> getAllCompanie() {
        return companyMapper.toCompanyDTOList((List<CompanyEntity>) companiesRepository.findAll());
    }
}
