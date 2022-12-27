package com.example.wegoogift.controller;

import com.example.wegoogift.entity.CompanyEntity;
import com.example.wegoogift.service.DepositService;
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

    private final DepositService depositServiceService;


    public CompanieController(DepositService depositServiceService) {
        this.depositServiceService = depositServiceService;
    }

    @GetMapping
    public ResponseEntity<List<CompanyEntity>> getAllCompanie(){
        return ResponseEntity.ok(depositServiceService.getAllCompanie());
    }
}
