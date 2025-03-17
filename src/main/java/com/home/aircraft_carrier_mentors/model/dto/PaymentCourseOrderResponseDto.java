package com.home.aircraft_carrier_mentors.model.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCourseOrderResponseDto  implements Serializable {
    private UUID id;
    private Long courseId;
    private Long buyerId;
    private BigDecimal amount;
    private Instant createdAt;
}
