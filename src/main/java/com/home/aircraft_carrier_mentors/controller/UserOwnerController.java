package com.home.aircraft_carrier_mentors.controller;

import com.home.aircraft_carrier_mentors.model.dto.UserOwnerRequestDto;
import com.home.aircraft_carrier_mentors.model.dto.UserOwnerResponseDto;
import com.home.aircraft_carrier_mentors.service.user_owner.UserOwnerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("api/v1/user-owner")
@RequiredArgsConstructor
public class UserOwnerController {
    private final UserOwnerService userOwnerServiceImpl;

    @PostMapping
    public UserOwnerResponseDto createUserOwner(@RequestBody @NotNull @Valid UserOwnerRequestDto requestDto) {
        return userOwnerServiceImpl.createUserOwner(requestDto);
    }

    @GetMapping("/{id}")
    public UserOwnerResponseDto getUserOwner(@PathVariable("id") @Positive @NotNull Long id) {
        return userOwnerServiceImpl.getUserOwner(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserOwnerById(@PathVariable("id") @Positive @NotNull Long id) {
        userOwnerServiceImpl.deleteUserOwnerById(id);
    }
}
