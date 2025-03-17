package com.home.aircraft_carrier_mentors.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.home.aircraft_carrier_mentors.model.dto.PaymentCourseOrderResponseDto;
import com.home.aircraft_carrier_mentors.model.dto.PaymentCourseOrderRequestDto;
import com.home.aircraft_carrier_mentors.service.payment.PaymentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentServiceImpl;

    @PostMapping
    public PaymentCourseOrderResponseDto payCourse(@RequestBody @NotNull @Valid PaymentCourseOrderRequestDto requestDto) throws JsonProcessingException {
        return paymentServiceImpl.payCourse(requestDto);
    }
}
