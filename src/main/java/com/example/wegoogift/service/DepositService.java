package com.example.wegoogift.service;

import com.example.wegoogift.dto.DepositDTO;
import com.example.wegoogift.entity.CompanyEntity;
import com.example.wegoogift.entity.DepositEntity;

import java.util.List;

public interface DepositService {
    List<CompanyEntity> getAllCompanie();

    DepositEntity destributeDeposite(Long companieId, Long userId, DepositDTO depositDTO);
}
