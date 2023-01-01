package com.example.wegoogift.model.dto;

import java.time.LocalDateTime;
public class GiftDepositDTO extends DepositDTO{

    public GiftDepositDTO(Double amount, LocalDateTime beginDate, String depositType) {
        super(amount, beginDate, depositType);
        super.endDate =LocalDateTime.from(beginDate)
                .plusDays(365);
    }
}
