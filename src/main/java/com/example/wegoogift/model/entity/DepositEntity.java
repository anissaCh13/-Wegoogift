package com.example.wegoogift.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="DEPOSIT")
public class DepositEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEPOSIT_SEQ")
    @SequenceGenerator(name = "DEPOSIT_SEQ", sequenceName = "DEPOSIT_SEQ", allocationSize = 1)
    @Column(unique = true, nullable = false)
    private Long id;
    @Column
    private Double amount;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="companie_id", nullable = false)
    @JsonBackReference
    private CompanyEntity company;

    public DepositEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amout) {
        this.amount = amout;
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

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public String getDepositType() {
        return depositType;
    }

    public void setDepositType(String depositeType) {
        this.depositType = depositeType;
    }
}
