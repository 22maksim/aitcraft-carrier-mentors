package com.home.aircraft_carrier_mentors.sheduler;

import com.home.aircraft_carrier_mentors.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxPaymentOrderScheduled {
    private final PaymentService paymentServiceImpl;

    @Scheduled(cron = "0 0 * * * *")
    public void scheduledPaymentOrder() {
        paymentServiceImpl.findOldOrders();
        log.info("Scheduled old payment order is removed");
    }
}
