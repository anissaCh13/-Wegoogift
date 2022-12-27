package com.example.wegoogift.service.impl;

import com.example.wegoogift.dto.DepositDTO;
import com.example.wegoogift.entity.CompanyEntity;
import com.example.wegoogift.entity.UserEntity;
import com.example.wegoogift.entity.DepositEntity;
import com.example.wegoogift.exception.CompanieNotFound;
import com.example.wegoogift.exception.CompanyBalanceError;
import com.example.wegoogift.exception.UserNotFound;
import com.example.wegoogift.repository.CompaniesRepository;
import com.example.wegoogift.repository.UserRepository;
import com.example.wegoogift.repository.DepositRepository;
import com.example.wegoogift.service.DepositService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositServiceImpl implements DepositService {

    CompaniesRepository companiesRepository;
    UserRepository userRepository;

    DepositRepository depositRepository;

    public DepositServiceImpl(CompaniesRepository companiesRepository, UserRepository userRepository, DepositRepository depositRepository) {
        this.companiesRepository = companiesRepository;
        this.userRepository = userRepository;
        this.depositRepository = depositRepository;
    }

    @Override
    public List<CompanyEntity> getAllCompanie() {
        return (List<CompanyEntity>) companiesRepository.findAll();
    }

    @Override
    public DepositEntity destributeDeposite(Long companieId, Long userId, DepositDTO depositDTO) {
        CompanyEntity companyEntity = companiesRepository.findById(companieId)
                .orElseThrow(() ->
                        new CompanieNotFound(companieId.toString()));
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() ->
                new UserNotFound(userId.toString()));
        if (companyEntity.getBalance().compareTo(depositDTO.getAmount())<0){
            throw new CompanyBalanceError();
        }
        companyEntity.setBalance(companyEntity.getBalance() - depositDTO.getAmount());
        DepositEntity depositEntity = new DepositEntity();
        depositEntity.setAmout(depositDTO.getAmount());
        depositEntity.setCompanie(companyEntity);
        depositEntity.setUser(userEntity);
        depositEntity.setBeginDate(depositDTO.getBeginDate());
        depositEntity.setEndDate(depositDTO.getEndDate());
        depositEntity.setDepositType(depositDTO.getDepositType());

        return depositRepository.save(depositEntity);
    }
}
