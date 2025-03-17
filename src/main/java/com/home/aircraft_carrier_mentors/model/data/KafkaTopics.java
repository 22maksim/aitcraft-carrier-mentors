package com.home.aircraft_carrier_mentors.model.data;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "topics.kafka")
public record KafkaTopics(
        String outboxPayment
) {
}
