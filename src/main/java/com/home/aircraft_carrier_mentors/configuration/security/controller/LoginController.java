package com.home.aircraft_carrier_mentors.configuration.security.controller;

import com.home.aircraft_carrier_mentors.configuration.security.model.UserAccountDto;
import com.home.aircraft_carrier_mentors.configuration.security.sertvice.UserAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {
    private final UserAccountService userAccountService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<String> login(@RequestBody @Valid UserAccountDto userLoginDto) {
        return userAccountService.login(userLoginDto);
    }
}
