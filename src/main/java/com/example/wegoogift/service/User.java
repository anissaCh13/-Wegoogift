package com.example.wegoogift.service;

import com.example.wegoogift.model.dto.UserBalance;

public interface User {

    UserBalance returnUserBalance(Long idUser);
}
