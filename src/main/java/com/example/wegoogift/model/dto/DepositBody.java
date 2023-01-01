package com.example.wegoogift.model.dto;

import java.time.LocalDateTime;

public record DepositBody(Long userId,
                          Long companieId,
                          Double amount,
                          LocalDateTime beginDate,
                          String depositType) {
}
