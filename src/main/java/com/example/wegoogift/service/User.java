package com.example.wegoogift.service;

import com.example.wegoogift.dto.UserBalance;

public interface User {

    UserBalance returnUserBalance(Long idUser);
}
