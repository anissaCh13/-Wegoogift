package com.example.wegoogift.service.impl;

import com.example.wegoogift.exception.UserOutOfCompany;
import com.example.wegoogift.mapper.DepositMapper;
import com.example.wegoogift.model.dto.*;
import com.example.wegoogift.model.entity.CompanyEntity;
import com.example.wegoogift.model.entity.UserEntity;
import com.example.wegoogift.model.entity.DepositEntity;
import com.example.wegoogift.exception.CompanieNotFound;
import com.example.wegoogift.exception.CompanyBalanceError;
import com.example.wegoogift.exception.UserNotFound;
import com.example.wegoogift.model.enums.DepositType;
import com.example.wegoogift.repository.CompaniesRepository;
import com.example.wegoogift.repository.UserRepository;
import com.example.wegoogift.repository.DepositRepository;
import com.example.wegoogift.service.DepositService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepositServiceImpl implements DepositService {

    CompaniesRepository companiesRepository;

    UserRepository userRepository;

    DepositRepository depositRepository;

    DepositMapper depositMapper;

    public DepositServiceImpl(CompaniesRepository companiesRepository,
                              UserRepository userRepository,
                              DepositRepository depositRepository,
                              DepositMapper depositMapper){
        this.companiesRepository = companiesRepository;
        this.userRepository = userRepository;
        this.depositRepository = depositRepository;
        this.depositMapper = depositMapper;
    }

    @Override
    public DepositDTO destributeDeposite(DepositBody depositBody) {
        UserEntity userEntity = getUser(depositBody);
        CompanyEntity companyEntity = getCompany(depositBody);
        checkUserInCompany(depositBody, userEntity);
        if (companyEntity.getBalance().compareTo(depositBody.amount()) < 0) {
            throw new CompanyBalanceError();
        }

        DepositDTO depositDTO = initiatDepositDTO(depositBody);
        companyEntity.setBalance(companyEntity.getBalance() - depositBody.amount());

        DepositEntity depositEntity = depositMapper.toDepositEntity(depositDTO, companyEntity, userEntity);

        return depositMapper.toDepositDTO(depositRepository.save(depositEntity));
    }

    private static DepositDTO initiatDepositDTO(DepositBody depositBody) {
        return DepositType.GIFT.name().equals(depositBody.depositType().name()) ?
                new GiftDepositDTO(depositBody.amount(), depositBody.beginDate(), depositBody.depositType().name())
                : new MealDepositDTO(depositBody.amount(), depositBody.beginDate(), depositBody.depositType().name());
    }

    private void checkUserInCompany(DepositBody depositBody, UserEntity userEntity) {
        Optional<CompanyEntity> companyEntityOptional = Optional.ofNullable(companiesRepository.findByUsersAndId(userEntity, depositBody.companieId()));
        companyEntityOptional.orElseThrow(()-> new UserOutOfCompany(userEntity.getFirstName()));
    }

    private CompanyEntity getCompany(DepositBody depositBody) {
        return companiesRepository.findById(depositBody.companieId())
                .orElseThrow(() ->
                        new CompanieNotFound(depositBody.companieId().toString()));
    }

    private UserEntity getUser(DepositBody depositBody) {
        UserEntity userEntity = userRepository.findById(depositBody.userId())
                .orElseThrow(() ->
                        new UserNotFound(depositBody.userId().toString()));
        return userEntity;
    }
}
