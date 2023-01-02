package com.example.wegoogift.model.dto;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class MealDepositDTO extends DepositDTO {
    public MealDepositDTO(Double amount, LocalDateTime beginDate, String depositType) {
        super(amount, beginDate, depositType);
        int year = LocalDateTime.from(beginDate).plusYears(1).getYear();
        super.endDate = LocalDateTime.of(year, 2, 1, 0, 0).with(TemporalAdjusters.lastDayOfMonth());
    }

}
