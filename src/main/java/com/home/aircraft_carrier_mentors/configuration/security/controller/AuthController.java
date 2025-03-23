package com.home.aircraft_carrier_mentors.configuration.security.controller;

import com.home.aircraft_carrier_mentors.configuration.security.model.UserAccountDto;
import com.home.aircraft_carrier_mentors.configuration.security.sertvice.UserAccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserAccountService userAccountService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @NotNull @Valid UserAccountDto registerDto) {
        userAccountService.registerUser(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Пользователь зарегистрирован");
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/register/admin")
    public ResponseEntity<String> registerAdmin(@RequestBody @NotNull @Valid UserAccountDto registerDto) {
        userAccountService.registerAdmin(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Администратор зарегистрирован");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @NotNull @Valid UserAccountDto userLoginDto) {
        String token = userAccountService.login(userLoginDto);
        return ResponseEntity.ok(token);
    }
}

