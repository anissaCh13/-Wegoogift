package com.example.wegoogift.controller;

import com.example.wegoogift.exception.ErrorHandler;
import com.example.wegoogift.exception.UserNotFound;
import com.example.wegoogift.model.dto.UserBalance;
import com.example.wegoogift.service.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {


    @InjectMocks
    UserController userController;

    @Mock
    User userService;

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new ErrorHandler())
                .build();


    }

    @Test
    void userBalance() {
        when(userService.returnUserBalance(1L)).thenReturn(new UserBalance(200.0, 300.0));

        ResponseEntity<UserBalance> responseEntity = userController.userBalance(1L);

        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();
    }

    @Test
    void testException() throws Exception {
        when(userService.returnUserBalance(20L)).thenThrow(UserNotFound.class);
        MockHttpServletResponse response= mvc.perform(MockMvcRequestBuilders.get("/user/20")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

    }
}
