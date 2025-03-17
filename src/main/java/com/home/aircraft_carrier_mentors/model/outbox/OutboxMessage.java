package com.home.aircraft_carrier_mentors.model.outbox;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OutboxMessage implements Serializable {
    private UUID id;
    private String prefix;
    private JsonNode content;
}
