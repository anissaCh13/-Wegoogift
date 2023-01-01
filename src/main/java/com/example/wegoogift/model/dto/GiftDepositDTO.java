package com.example.wegoogift.model.dto;

import java.time.LocalDateTime;

import static com.example.wegoogift.model.constants.DayConstant.LIFESPAN;

public class GiftDepositDTO extends DepositDTO{

    public GiftDepositDTO(Double amount, LocalDateTime beginDate, String depositType) {
        super(amount, beginDate, depositType);
        super.endDate =LocalDateTime.from(beginDate)
                .plusDays(LIFESPAN);
    }
}
