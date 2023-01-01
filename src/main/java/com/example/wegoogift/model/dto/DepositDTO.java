package com.example.wegoogift.model.dto;

import java.time.LocalDateTime;

public class DepositDTO {

    protected Double amount;

    protected LocalDateTime beginDate;

    protected LocalDateTime endDate;

    protected String depositType;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDateTime beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getDepositType() {
        return depositType;
    }

    public void setDepositType(String depositType) {
        this.depositType = depositType;
    }

    public DepositDTO(Double amount, LocalDateTime beginDate, String depositType) {
        this.amount = amount;
        this.beginDate = beginDate;
        this.depositType = depositType;
    }
}
