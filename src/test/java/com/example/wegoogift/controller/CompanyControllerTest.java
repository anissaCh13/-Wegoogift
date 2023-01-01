package com.example.wegoogift.controller;

import com.example.wegoogift.model.dto.CompanyDTO;
import com.example.wegoogift.service.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompanyControllerTest {

    @InjectMocks
    CompanyController companyController;

    @Mock
    CompanyService companyService;

    @Test
    void getAllCompanie() {
        CompanyDTO companyDTO1 = new CompanyDTO(1L, "Tesla", 2000.0, new ArrayList<>(), new ArrayList<>());
        CompanyDTO companyDTO2 = new CompanyDTO(1L, "Apple", 5000.0, new ArrayList<>(), new ArrayList<>());
        CompanyDTO companyDTO3 = new CompanyDTO(1L, "LG", 4000.0, new ArrayList<>(), new ArrayList<>());
        List<CompanyDTO> companyDTOList = new ArrayList<>() {{
            add(companyDTO1);
            add(companyDTO2);
            add(companyDTO3);
        }};

        when(companyService.getAllCompanie()).thenReturn(companyDTOList);

        ResponseEntity<List<CompanyDTO>> responseEntity = companyController.getAllCompanie();

        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isEqualTo(true);
        assertThat(responseEntity.getBody().size()).isEqualTo(3);
    }
}
