package com.example.wegoogift.service;

import com.example.wegoogift.model.dto.DepositDTO;
import com.example.wegoogift.model.dto.DepositBody;

public interface DepositService {

    DepositDTO destributeDeposite(DepositBody depositBody);
}
