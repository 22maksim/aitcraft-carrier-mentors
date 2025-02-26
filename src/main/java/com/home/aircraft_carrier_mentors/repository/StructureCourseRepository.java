package com.home.aircraft_carrier_mentors.repository;

import com.home.aircraft_carrier_mentors.model.StructureCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StructureCourseRepository extends JpaRepository<StructureCourse,Long> {
}
