package com.home.aircraft_carrier_mentors.model.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.home.aircraft_carrier_mentors.model.outbox.OrchestratorStatus;
import com.home.aircraft_carrier_mentors.model.outbox.OutboxType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnTransformer;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@Table(name = "outbox_payment")
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class OutboxPayment implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    @JsonProperty("outbox_type")
    @Column(name = "outbox_type", nullable = false)
    private OutboxType outboxType;

    @Column(name = "prefix", nullable = false)
    private String prefix;

    @Column(columnDefinition = "jsonb")
    @ColumnTransformer(write = "?::jsonb")
    private String content;

    @Enumerated(EnumType.STRING)
    @JsonProperty("orchestrator_status")
    @Column(name = "orchestrator_status", nullable = false)
    private OrchestratorStatus orchestratorStatus;

    @Column(columnDefinition = "jsonb")
    @ColumnTransformer(write = "?::jsonb")
    private String processes;

    @CreatedDate
    @JsonProperty("created_at")
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;


}
