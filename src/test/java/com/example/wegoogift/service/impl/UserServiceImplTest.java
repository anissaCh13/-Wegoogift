package com.example.wegoogift.service.impl;

import com.example.wegoogift.exception.UserNotFound;
import com.example.wegoogift.mapper.DepositMapper;
import com.example.wegoogift.model.dto.DepositDTO;
import com.example.wegoogift.model.dto.UserBalance;
import com.example.wegoogift.model.entity.DepositEntity;
import com.example.wegoogift.model.entity.UserEntity;
import com.example.wegoogift.model.enums.DepositType;
import com.example.wegoogift.repository.UserRepository;
import com.example.wegoogift.service.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private User user;

    @Mock
    UserRepository userRepository;

    @Mock
    DepositMapper depositMapper;

    @BeforeEach
    void setUp() {
        user = new UserServiceImpl(userRepository, depositMapper);
    }

    private List<DepositDTO> initDepositList() {
        DepositDTO depositDTO1 = new DepositDTO(100.0, LocalDateTime.now(), DepositType.GIFT.name());
        depositDTO1.setEndDate(LocalDateTime.now());
        DepositDTO depositDTO2 = new DepositDTO(200.0, LocalDateTime.now(), DepositType.GIFT.name());
        depositDTO1.setEndDate(LocalDateTime.now());
        DepositDTO depositDTO3 = new DepositDTO(400.0, LocalDateTime.now(), DepositType.MEAL.name());
        depositDTO1.setEndDate(LocalDateTime.now());
        DepositDTO depositDTO4 = new DepositDTO(300.0, LocalDateTime.now(), DepositType.MEAL.name());
        depositDTO1.setEndDate(LocalDateTime.now());
        List<DepositDTO> depositDTOS = new ArrayList<>(){{add(depositDTO1); add(depositDTO2); add(depositDTO3); add(depositDTO4);}};
        return depositDTOS;
    }

    private UserEntity initUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setFirstName("firstName");
        userEntity.setLastName("lastName");

        DepositEntity depositEntity = new DepositEntity();
        depositEntity.setDepositType(DepositType.GIFT.name());
        depositEntity.setEndDate(LocalDateTime.now());
        depositEntity.setBeginDate(LocalDateTime.now());
        depositEntity.setAmount(100.0);
        depositEntity.setId(1L);
        List<DepositEntity> depositEntities = new ArrayList<>(){{add(depositEntity);}};
        userEntity.setDepositEntities(depositEntities);
        return userEntity;
    }

    @Test
    void canReturnTheSumOfMealAndGiftBalance(){
        //given
        UserEntity userEntity = initUserEntity();
        List<DepositDTO> depositDTOS = initDepositList();

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));


        when(depositMapper.toDepositDTOList(userEntity.getDepositEntities())).thenReturn(depositDTOS);

        //when
        UserBalance result = user.returnUserBalance(anyLong());

        //then
        verify(userRepository).findById(anyLong());
        verify(depositMapper).toDepositDTOList(anyList());
        assertEquals(result, new UserBalance(300.0, 700.0));
    }

    @Test
    public void shouldThrowExceptionIfUserIdNotFound(){
        assertThrows(UserNotFound.class, () -> {
            user.returnUserBalance(anyLong());
        });

    }
}
