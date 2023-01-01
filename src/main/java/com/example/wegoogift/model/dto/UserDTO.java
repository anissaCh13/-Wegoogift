package com.example.wegoogift.model.dto;

import java.util.List;

public record UserDTO(Long id,
                      String firstName,
                      String lastName,
                      List<DepositDTO> giftDepositDTO) {
}
