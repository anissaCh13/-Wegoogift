package com.example.wegoogift.service.impl;

import com.example.wegoogift.mapper.CompanyMapper;
import com.example.wegoogift.repository.CompaniesRepository;
import com.example.wegoogift.service.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CompanyServiceImplTest {

    private CompanyService companyService;

    @Mock
    CompaniesRepository companiesRepository;
    @Mock
    CompanyMapper companyMapper;

    @BeforeEach
    void setUp() {
        companyService = new CompanyServiceImpl(companiesRepository, companyMapper);
    }

    @Test
    void canGetAllCompanie() {
        //when
        companyService.getAllCompanie();
        //then
        verify(companiesRepository).findAll();
        verify(companyMapper).toCompanyDTOList(anyList());

    }
}
