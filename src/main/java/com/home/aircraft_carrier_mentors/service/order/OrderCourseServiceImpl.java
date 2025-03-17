package com.home.aircraft_carrier_mentors.service.order;

import com.home.aircraft_carrier_mentors.model.payment.OutboxPayment;
import com.home.aircraft_carrier_mentors.model.payment.PaymentOrderFromCourse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderCourseServiceImpl implements OrderCourseService {

    @Override
    public void createOrderByCourse(OutboxPayment paymentOrderFromCourse) {
        System.out.println("Создан заказ. Принят в обработку. ВРЕМЯ : " + paymentOrderFromCourse.getCreatedAt());
    }
}
