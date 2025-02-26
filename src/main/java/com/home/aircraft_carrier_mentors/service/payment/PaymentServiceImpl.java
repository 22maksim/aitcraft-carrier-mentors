package com.home.aircraft_carrier_mentors.service.payment;

import com.home.aircraft_carrier_mentors.model.dto.PaymentCourseRequestDto;
import com.home.aircraft_carrier_mentors.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Override
    public String payCourse(PaymentCourseRequestDto requestDto) {

        return "";
    }
}
