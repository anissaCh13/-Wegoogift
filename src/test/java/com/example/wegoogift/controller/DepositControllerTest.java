package com.example.wegoogift.controller;

import com.example.wegoogift.model.dto.DepositBody;
import com.example.wegoogift.model.dto.DepositDTO;
import com.example.wegoogift.model.dto.GiftDepositDTO;
import com.example.wegoogift.model.enums.DepositType;
import com.example.wegoogift.service.DepositService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepositControllerTest {

    @InjectMocks
    DepositController depositController;

    @Mock
    DepositService depositService;

    @Test
    void distributeDeposite() {
        DepositDTO depositDTO = new GiftDepositDTO(100.0, LocalDateTime.now(), "GIFT");

        when(depositService.destributeDeposite(any(DepositBody.class))).thenReturn(depositDTO);
        DepositBody depositBody = new DepositBody(1L, 1L, 100.0, LocalDateTime.now(), DepositType.GIFT);
        ResponseEntity<DepositDTO> responseEntity = depositController.distributeDeposite(depositBody);

        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(Objects.requireNonNull(responseEntity.getBody()).getEndDate()).isNotNull();
    }
}
