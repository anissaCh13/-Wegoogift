package com.example.wegoogift.model.dto;

import java.util.List;

public record CompanyDTO(Long id, String name, Double balance, List<DepositDTO> depositDTOS, List<UserDTO> userDTOS) {
}
