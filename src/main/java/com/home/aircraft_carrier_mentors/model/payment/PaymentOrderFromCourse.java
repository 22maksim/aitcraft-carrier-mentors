package com.home.aircraft_carrier_mentors.model.payment;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment_order_from_course")
@EntityListeners(AuditingEntityListener.class)
public class PaymentOrderFromCourse implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Column(name = "amount", nullable = false, precision = 19, scale = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal amount;

    @Column(name = "buyer_id", nullable = false)
    private Long buyerId;

    @Column(name = "description")
    private String description;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant createdAt;
}
