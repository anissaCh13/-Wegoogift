package com.example.wegoogift.dto;

import java.time.LocalDateTime;
public class GiftDepositDTO extends DepositDTO{

    @Override
    public LocalDateTime getEndDate(){
        super.endDate = LocalDateTime.from(super.beginDate)
                .plusDays(365);
        return endDate;
    }
}
