package com.home.aircraft_carrier_mentors.service.payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.home.aircraft_carrier_mentors.model.dto.PaymentCourseOrderResponseDto;
import com.home.aircraft_carrier_mentors.model.dto.PaymentCourseOrderRequestDto;

public interface PaymentService {

    PaymentCourseOrderResponseDto payCourse(PaymentCourseOrderRequestDto requestDto) throws JsonProcessingException;

    void findOldOrders();

}
