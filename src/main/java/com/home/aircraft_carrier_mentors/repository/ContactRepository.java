package com.home.aircraft_carrier_mentors.repository;

import com.home.aircraft_carrier_mentors.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{
}
