package com.example.wegoogift.model.dto;

import java.time.LocalDateTime;

public class MealDepositDTO extends DepositDTO {
    public MealDepositDTO(Double amount, LocalDateTime beginDate, String depositType) {
        super(amount, beginDate, depositType);
        int year = LocalDateTime.from(beginDate).plusYears(1).getYear();
        super.endDate = LocalDateTime.of(year, 2, 28, 0, 0);
    }

}
