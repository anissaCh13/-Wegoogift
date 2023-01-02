package com.example.wegoogift.service.impl;

import com.example.wegoogift.exception.CompanieNotFound;
import com.example.wegoogift.exception.CompanyBalanceError;
import com.example.wegoogift.exception.UserNotFound;
import com.example.wegoogift.exception.UserOutOfCompany;
import com.example.wegoogift.mapper.DepositMapper;
import com.example.wegoogift.model.dto.DepositBody;
import com.example.wegoogift.model.dto.DepositDTO;
import com.example.wegoogift.model.entity.CompanyEntity;
import com.example.wegoogift.model.entity.DepositEntity;
import com.example.wegoogift.model.entity.UserEntity;
import com.example.wegoogift.model.enums.DepositType;
import com.example.wegoogift.repository.CompaniesRepository;
import com.example.wegoogift.repository.DepositRepository;
import com.example.wegoogift.repository.UserRepository;
import com.example.wegoogift.service.DepositService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepositServiceImplTest {

    DepositService depositService;

    @Mock
    UserRepository userRepository;

    @Mock
    DepositRepository depositRepository;

    @Mock
    DepositMapper depositMapper;

    @Mock
    CompaniesRepository companiesRepository;


    @BeforeEach
    void setUp() {
        depositService = new DepositServiceImpl(companiesRepository, userRepository, depositRepository, depositMapper);
    }

    private UserEntity initUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setFirstName("First_Name");
        userEntity.setLastName("Last_name");

        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setId(1L);
        companyEntity.setBalance(2000.0);
        companyEntity.setName("Tesla");
        List<CompanyEntity> companyEntities = List.of(companyEntity);
        userEntity.setCompanies(companyEntities);
        return userEntity;
    }

    private CompanyEntity initCompanyEntity() {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setId(1L);
        companyEntity.setBalance(2000.0);
        companyEntity.setName("Tesla");

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setFirstName("First_Name");
        userEntity.setLastName("Last_name");
        List<UserEntity> userEntityList = List.of(userEntity);
        companyEntity.setUsers(userEntityList);
        return companyEntity;
    }

    private DepositEntity initDepositEntity(){
        DepositEntity depositEntity = new DepositEntity();
        depositEntity.setAmount(100.0);
        depositEntity.setDepositType(DepositType.GIFT.name());
        depositEntity.setId(1L);
        depositEntity.setBeginDate(LocalDateTime.now());
        depositEntity.setCompany(initCompanyEntity());
        depositEntity.setUser(initUserEntity());
        return depositEntity;
    }


    @Test
    void shouldThrowExceptionIfUserIsNull() {
        DepositBody depositBody = new DepositBody(2L, 1L, 200.0, LocalDateTime.now(), DepositType.GIFT);
        when(userRepository.findById(depositBody.userId())).thenReturn(Optional.empty());
        assertThrows(UserNotFound.class, () -> {
            depositService.destributeDeposite(depositBody);
        });
    }

    @Test
    void shouldThrowExceptionIfCompanyIsNull() {
        DepositBody depositBody = new DepositBody(1L, 2L, 200.0, LocalDateTime.now(), DepositType.GIFT);
        when(userRepository.findById(depositBody.userId())).thenReturn(Optional.of(initUserEntity()));
        when(companiesRepository.findById(depositBody.companieId())).thenReturn(Optional.empty());
        assertThrows(CompanieNotFound.class, () -> {
            depositService.destributeDeposite(depositBody);
        });
    }

    @Test
    void shouldThrowExceptionIfUserIsNotPartOfTheCompany() {
        DepositBody depositBody = new DepositBody(1L, 2L, 200.0, LocalDateTime.now(), DepositType.GIFT);
        when(userRepository.findById(depositBody.userId())).thenReturn(Optional.of(initUserEntity()));
        when(companiesRepository.findById(depositBody.companieId())).thenReturn(Optional.of(initCompanyEntity()));
        UserEntity userEntity = userRepository.findById(depositBody.userId()).get();
        when(companiesRepository.findByUsersAndId(userEntity, depositBody.companieId())).thenReturn(null);
        assertThrows(UserOutOfCompany.class, () -> {
            depositService.destributeDeposite(depositBody);
        });
    }

    @Test
    void shouldThrowExceptionIfCompanyDoesntAcceptTheDeposit(){
        DepositBody depositBody = new DepositBody(1L, 2L, 50000.0, LocalDateTime.now(), DepositType.GIFT);
        when(userRepository.findById(depositBody.userId())).thenReturn(Optional.of(initUserEntity()));
        when(companiesRepository.findById(depositBody.companieId())).thenReturn(Optional.of(initCompanyEntity()));
        UserEntity userEntity = userRepository.findById(depositBody.userId()).get();
        when(companiesRepository.findByUsersAndId(userEntity, depositBody.companieId())).thenReturn(initCompanyEntity());
        assertThrows(CompanyBalanceError.class, () -> {
            depositService.destributeDeposite(depositBody);
        });
    }

    @Test
    void shouldDestributeDeposite() {
        DepositBody depositBody = new DepositBody(1L, 2L, 100.0, LocalDateTime.now(), DepositType.GIFT);
        when(userRepository.findById(depositBody.userId())).thenReturn(Optional.of(initUserEntity()));
        when(companiesRepository.findById(depositBody.companieId())).thenReturn(Optional.of(initCompanyEntity()));
        UserEntity userEntity = userRepository.findById(depositBody.userId()).get();
        when(companiesRepository.findByUsersAndId(userEntity, depositBody.companieId())).thenReturn(initCompanyEntity());
        when(depositMapper.toDepositEntity(any(), any(), any())).thenReturn(initDepositEntity());

        depositService.destributeDeposite(depositBody);

        verify(depositRepository).save(depositMapper.toDepositEntity(any(), any(), any()));
    }

    @Test
    void shouldVerifyTheEndDate_GiftDeposit() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = DepositServiceImpl.class.getDeclaredMethod("initiatDepositDTO", DepositBody.class);
        method.setAccessible(true);
        DepositDTO depositDTO = (DepositDTO) method.invoke(depositService, new DepositBody(1L,1L,100.0,
                LocalDateTime.of(2021,06,15,0,0),DepositType.GIFT));
        assertEquals(LocalDateTime.of(2022,06,14,0,0), depositDTO.getEndDate());
    }

    @Test
    void shouldVerifyTheEndDate_MealDeposit() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = DepositServiceImpl.class.getDeclaredMethod("initiatDepositDTO", DepositBody.class);
        method.setAccessible(true);
        DepositDTO depositDTO = (DepositDTO) method.invoke(depositService, new DepositBody(1L,1L,100.0,
                LocalDateTime.of(2020,01,01,0,0),DepositType.MEAL));
        assertEquals(LocalDateTime.of(2021,02,28,0,0), depositDTO.getEndDate());
    }
}
