package com.example.wegoogift.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Table(name= "Companies")
public class CompanyEntity {

    @Id
    @Column(name="companie_id",unique = true, nullable = false)
    private Long id;

    @NotNull
    @Column(name="companie_name", nullable = false)
    private String name;

    @NotNull
    @Column(name="balance")
    private Double balance;

    @OneToMany(mappedBy = "companie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<DepositEntity> depositEntities;

    @ManyToMany(mappedBy = "companies")
    @JsonManagedReference
    private List<UserEntity> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<DepositEntity> getGiftDeposits() {
        return depositEntities;
    }

    public void setGiftDeposits(List<DepositEntity> user) {
        this.depositEntities = user;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> userEntities) {
        this.users = userEntities;
    }
}
