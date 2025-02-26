package com.home.aircraft_carrier_mentors.repository;

import com.home.aircraft_carrier_mentors.model.CourseOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseOwnerRepository extends JpaRepository<CourseOwner,Long> {
}
