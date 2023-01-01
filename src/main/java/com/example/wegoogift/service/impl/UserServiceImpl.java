package com.example.wegoogift.service.impl;

import com.example.wegoogift.model.dto.DepositDTO;
import com.example.wegoogift.model.dto.UserBalance;
import com.example.wegoogift.exception.UserNotFound;
import com.example.wegoogift.mapper.DepositMapper;
import com.example.wegoogift.model.enums.DepositType;
import com.example.wegoogift.repository.UserRepository;
import com.example.wegoogift.service.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements User {

    UserRepository userRepository;

    DepositMapper depositMapper;

    public UserServiceImpl(UserRepository userRepository, DepositMapper depositMapper) {
        this.userRepository = userRepository;
        this.depositMapper = depositMapper;
    }

    @Override
    public UserBalance returnUserBalance(Long idUser) {
        List<DepositDTO> depositEntities = depositMapper.toDepositDTOList(userRepository.findById(idUser)
                .orElseThrow(() ->
                        new UserNotFound(idUser.toString()))
                .getDepositEntities());

        Double giftBalance = getBalance(depositEntities, DepositType.GIFT.name());
        Double mealBalance = getBalance(depositEntities, DepositType.MEAL.name());
        return new UserBalance(giftBalance, mealBalance);
    }


    private Double getBalance(List<DepositDTO> depositEntities, String depositType) {
        return depositEntities.stream()
                    .filter(t->depositType.equals(t.getDepositType()))
                    .mapToDouble(DepositDTO::getAmount)
                    .sum();
    }
}
