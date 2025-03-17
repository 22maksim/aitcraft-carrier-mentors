package com.home.aircraft_carrier_mentors.repository;

import com.home.aircraft_carrier_mentors.model.payment.OutboxPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.UUID;

@Repository
public interface OutboxPaymentRepository extends JpaRepository<OutboxPayment, UUID> {

    @Query("DELETE FROM OutboxPayment p WHERE p.createdAt >  :date")
    void deleteWhereCreatedAtAfter(Instant date);

}
