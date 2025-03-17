package com.home.aircraft_carrier_mentors.repository;

import com.home.aircraft_carrier_mentors.model.payment.PaymentOrderFromCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentOrderFromCourseRepository extends JpaRepository<PaymentOrderFromCourse, UUID> {
}
