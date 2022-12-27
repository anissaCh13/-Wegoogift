package com.example.wegoogift.controller;

import com.example.wegoogift.dto.UserBalance;
import com.example.wegoogift.service.User;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final User user;

    public UserController(User user) {
        this.user = user;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserBalance> userBalance(@PathVariable("id") Long userId){
        return ResponseEntity.ok(user.returnUserBalance(userId));
    }
}
