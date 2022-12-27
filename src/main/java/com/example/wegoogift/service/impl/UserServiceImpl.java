package com.example.wegoogift.service.impl;

import com.example.wegoogift.dto.UserBalance;
import com.example.wegoogift.entity.DepositEntity;
import com.example.wegoogift.exception.UserNotFound;
import com.example.wegoogift.repository.UserRepository;
import com.example.wegoogift.service.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements User {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserBalance returnUserBalance(Long idUser) {
        List<DepositEntity> depositEntities = userRepository.findById(idUser)
                .orElseThrow(() ->
                        new UserNotFound(idUser.toString()))
                .getGiftDeposits();
        Double giftBalance= depositEntities.stream()
                .filter(t->t.getDepositType().equals("Gift"))
                .mapToDouble(s-> s.getAmout())
                .sum();
        Double mealBalance = depositEntities.stream()
                .filter(t->t.getDepositType().equals("Meal"))
                .mapToDouble(s-> s.getAmout())
                .sum();
        return new UserBalance(giftBalance, mealBalance);
    }
}
