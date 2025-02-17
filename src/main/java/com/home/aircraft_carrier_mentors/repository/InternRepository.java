package com.home.aircraft_carrier_mentors.repository;

import com.home.aircraft_carrier_mentors.model.Intern;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternRepository extends JpaRepository<Intern, Long> {
}