package com.home.aircraft_carrier_mentors.controller;

import com.home.aircraft_carrier_mentors.model.dto.PaymentCourseRequestDto;
import com.home.aircraft_carrier_mentors.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentServiceImpl;

    @PostMapping
    public ResponseEntity<String> payCourse(PaymentCourseRequestDto requestDto) {
        return ResponseEntity.ok()
                .headers(HttpHeaders.EMPTY) // тут начиннётся логика оплаты, не забудь ;-))
                .body(paymentServiceImpl.payCourse(requestDto));
    }
}
