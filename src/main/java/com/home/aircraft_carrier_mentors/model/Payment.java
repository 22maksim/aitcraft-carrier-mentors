package com.home.aircraft_carrier_mentors.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Builder
@Table(name = "payment")
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id; // должен быть строковый вид id для платежной системы
//    private String paymentId; // здесь надо подумать, нужен ли отдельный ключ

    @Column(name = "amount",  nullable = false, precision = 19, scale = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal amount;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "description")
    private String description;
}
