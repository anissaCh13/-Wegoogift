package com.example.wegoogift.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="GIFT_DEPOSIT")
public class DepositEntity {

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Long giftDepositId;
    @Column
    private Double amout;

    @Column
    private LocalDateTime beginDate;

    @Column
    private LocalDateTime endDate;

    @Column
    private String depositType;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    @JsonBackReference
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name="companie_id", nullable = false)
    @JsonBackReference
    private CompanyEntity companie;

    public DepositEntity() {

    }

    public Long getGiftDepositId() {
        return giftDepositId;
    }

    public void setGiftDepositId(Long giftDepositId) {
        this.giftDepositId = giftDepositId;
    }

    public Double getAmout() {
        return amout;
    }

    public void setAmout(Double amout) {
        this.amout = amout;
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity userEntity) {
        this.user = userEntity;
    }

    public CompanyEntity getCompanie() {
        return companie;
    }

    public void setCompanie(CompanyEntity companie) {
        this.companie = companie;
    }

    public String getDepositType() {
        return depositType;
    }

    public void setDepositType(String depositeType) {
        this.depositType = depositeType;
    }
}
