package com.example.wegoogift.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="T_User")
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(name="User_Id", unique = true, nullable = false)
    private Long id;

    @NotNull
    @Column
    private String firstName;

    @NotNull
    @Column
    private String lastName;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<DepositEntity> depositEntities;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="Companie_User",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "companie_id")} )
    @JsonBackReference
    private List<CompanyEntity> companies;

    public UserEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<DepositEntity> getGiftDeposits() {
        return depositEntities;
    }

    public void setGiftDeposits(List<DepositEntity> depositEntities) {
        this.depositEntities = depositEntities;
    }

    public UserEntity(Long id) {
        this.id = id;
    }

    public List<CompanyEntity> getCompanies() {
        return companies;
    }

    public void setCompanies(List<CompanyEntity> companies) {
        this.companies = companies;
    }


}
