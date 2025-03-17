package com.home.aircraft_carrier_mentors.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.home.aircraft_carrier_mentors.model.enums.PaymentEventType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCourseOrderRequestDto implements Serializable {
    private String id;

    @NotNull
    private Long courseId;

    @NotNull
    private PaymentEventType  paymentEventType;

    @NotNull
    private Long buyerId; // скорее всего это будет интерн

    private String description;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal amount;
}
