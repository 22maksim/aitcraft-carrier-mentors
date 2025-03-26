package com.home.aircraft_carrier_mentors.configuration.security.controller;

import com.home.aircraft_carrier_mentors.configuration.security.model.UserAccountDto;
import com.home.aircraft_carrier_mentors.configuration.security.model.UserAccountResponseDto;
import com.home.aircraft_carrier_mentors.configuration.security.sertvice.UserAccountService;
import com.home.aircraft_carrier_mentors.model.dto.UserOwnerResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
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
    public String registerUser(@RequestBody @NotNull @Valid UserAccountDto registerDto) {
        userAccountService.registerUser(registerDto);
        return "Пользователь зарегистрирован";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/register/admin")
    public String registerAdmin(@RequestBody @NotNull @Valid UserAccountDto registerDto) {
        userAccountService.registerAdmin(registerDto);
        return "Администратор зарегистрирован";
    }

    @PostMapping("/login")
    public ResponseEntity<UserOwnerResponseDto> login(@RequestBody @NotNull @Valid UserAccountDto userLoginDto) {
        UserAccountResponseDto accountResponseDto = userAccountService.login(userLoginDto);
        if (accountResponseDto == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = accountResponseDto.getToken();
        UserOwnerResponseDto userOwnerResponseDto = accountResponseDto.getUser();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, token);

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(userOwnerResponseDto);
    }
}

