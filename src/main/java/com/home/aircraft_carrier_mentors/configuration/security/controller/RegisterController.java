package com.home.aircraft_carrier_mentors.configuration.security.controller;

import com.home.aircraft_carrier_mentors.configuration.security.sertvice.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/register")
@RequiredArgsConstructor
public class RegisterController {
    private final UserAccountService userAccountService;

    @PostMapping
    public ResponseEntity<String> register(String username, String password) {
        return userAccountService.register(username, password);
    }
}
