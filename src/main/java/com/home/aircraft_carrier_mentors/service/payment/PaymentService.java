package com.home.aircraft_carrier_mentors.service.payment;

import com.home.aircraft_carrier_mentors.model.dto.PaymentCourseRequestDto;

public interface PaymentService {
    String payCourse(PaymentCourseRequestDto requestDto);
}
