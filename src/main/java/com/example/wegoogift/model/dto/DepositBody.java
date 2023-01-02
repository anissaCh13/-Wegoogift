package com.example.wegoogift.model.dto;

import com.example.wegoogift.model.enums.DepositType;

import java.time.LocalDateTime;

public record DepositBody(Long userId,
                          Long companieId,
                          Double amount,
                          LocalDateTime beginDate,
                          DepositType depositType) {
}
