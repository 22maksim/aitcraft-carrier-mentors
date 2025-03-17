package com.home.aircraft_carrier_mentors.service.order;

import com.home.aircraft_carrier_mentors.model.payment.OutboxPayment;

public interface OrderCourseService {
    void createOrderByCourse(OutboxPayment paymentOrderFromCourse);
}
