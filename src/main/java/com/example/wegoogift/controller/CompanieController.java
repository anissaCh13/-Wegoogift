package com.example.wegoogift.controller;

import com.example.wegoogift.model.dto.CompanyDTO;
import com.example.wegoogift.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping(value = "/companie")
public class CompanieController {

    private final CompanyService companyService;


    public CompanieController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAllCompanie(){
        return ResponseEntity.ok(companyService.getAllCompanie());
    }
}
