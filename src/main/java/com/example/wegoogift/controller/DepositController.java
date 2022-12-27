package com.example.wegoogift.controller;

import com.example.wegoogift.dto.DepositDTO;
import com.example.wegoogift.dto.GiftDTOBody;
import com.example.wegoogift.dto.GiftDepositDTO;
import com.example.wegoogift.dto.MealDepositDTO;
import com.example.wegoogift.entity.DepositEntity;
import com.example.wegoogift.repository.UserRepository;
import com.example.wegoogift.repository.DepositRepository;
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

    private final DepositRepository depositRepository;

    private final UserRepository userRepository;

    public DepositController(DepositService depositServiceService, DepositRepository depositRepository, UserRepository userRepository) {
        this.depositServiceService = depositServiceService;
        this.depositRepository = depositRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<DepositEntity> distributeDeposite(@RequestBody GiftDTOBody giftDTOBody) {

        DepositDTO giftDeposit = "Gift".equals(giftDTOBody.depositType()) ? new GiftDepositDTO() : new MealDepositDTO();

            giftDeposit.setBeginDate(giftDTOBody.beginDate());
            giftDeposit.setAmount(giftDTOBody.amount());
            giftDeposit.setDepositType(giftDTOBody.depositType());

        return ResponseEntity.ok(depositServiceService.destributeDeposite(giftDTOBody.companieId(), giftDTOBody.userId(), giftDeposit));
    }

}
