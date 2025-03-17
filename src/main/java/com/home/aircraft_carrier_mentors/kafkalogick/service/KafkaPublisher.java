package com.home.aircraft_carrier_mentors.kafkalogick.service;

import com.home.aircraft_carrier_mentors.model.payment.OutboxPayment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaPublisher {
    private final KafkaTemplate<String, Object> myKafkaTemplate;

    public void sendMessageOutboxPayment(String topic, OutboxPayment message) {
        myKafkaTemplate.send(topic, message)
                .whenComplete((res, ex) -> {
                    if (ex != null) {
                        log.error("Message don't send in Kafka. Error: {}", ex.getMessage(), ex);
                    }
                });
    }
}
