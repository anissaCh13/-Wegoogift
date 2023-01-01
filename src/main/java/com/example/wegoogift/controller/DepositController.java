package com.example.wegoogift.controller;

import com.example.wegoogift.model.dto.DepositDTO;
import com.example.wegoogift.model.dto.DepositBody;
import com.example.wegoogift.service.DepositService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(value = "/deposit")
public class DepositController {
    private final DepositService depositServiceService;

    public DepositController(DepositService depositServiceService) {
        this.depositServiceService = depositServiceService;
    }

    @PostMapping
    public ResponseEntity<DepositDTO> distributeDeposite(@RequestBody DepositBody depositBody) {

        return ResponseEntity.ok(depositServiceService.destributeDeposite(depositBody));
    }

}
