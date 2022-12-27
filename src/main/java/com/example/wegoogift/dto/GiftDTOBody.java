package com.example.wegoogift.dto;

import java.time.LocalDateTime;

public record GiftDTOBody(Long userId,
                          Long companieId,
                          Double amount,
                          LocalDateTime beginDate,
                          String depositType) {
}
