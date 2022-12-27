package com.example.wegoogift.dto;

import java.time.LocalDateTime;

public class MealDepositDTO extends DepositDTO {
    @Override
    public LocalDateTime getEndDate() {
        int year = LocalDateTime.from(super.beginDate).plusYears(1).getYear();
        return LocalDateTime.of(year, 2, 28, 0, 0);
    }
}
