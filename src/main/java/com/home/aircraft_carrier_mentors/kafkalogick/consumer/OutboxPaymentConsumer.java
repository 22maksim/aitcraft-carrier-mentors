package com.home.aircraft_carrier_mentors.kafkalogick.consumer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.aircraft_carrier_mentors.model.payment.OutboxPayment;
import com.home.aircraft_carrier_mentors.service.order.OrderCourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxPaymentConsumer {
    private final OrderCourseService orderCourseServiceImpl;
    private final ObjectMapper objectMapper;


    @KafkaListener(topics = "outbox_payment.public.outbox_payment", groupId = "my-group-2")
    public void onMessage(ConsumerRecord<String, byte[]> message, Acknowledgment acknowledgment) {
        OutboxPayment order = null;
        try {
            byte[] valueBytes = message.value();

            if (valueBytes != null) {
                String json = new String(valueBytes, StandardCharsets.UTF_8);
                JsonNode rootNode = objectMapper.readTree(json);

                String afterNode =  rootNode.path("payload").path("after").toString();
                if (!afterNode.isBlank()) {
                    order =  objectMapper.readValue(afterNode, OutboxPayment.class);
                    orderCourseServiceImpl.createOrderByCourse(order);
                } else {
                    throw new IllegalArgumentException("Payload is empty");
                }
            }

            acknowledgment.acknowledge();

        } catch (Exception e) {
            acknowledgment.nack(Duration.ofSeconds(1));
            log.error("Invalid send message. {}", e.getMessage());
        }

        log.info("Received message is Ok: {}", order);
    }
}
