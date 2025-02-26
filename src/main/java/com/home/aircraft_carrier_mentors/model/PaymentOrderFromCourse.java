package com.home.aircraft_carrier_mentors.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Builder
@Table(name = "payment_order_from_course")
@NoArgsConstructor
@AllArgsConstructor
public class PaymentOrderFromCourse {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Column(name = "orderr_owner_id", nullable = false)
    private Long orderOwnerId;
}
