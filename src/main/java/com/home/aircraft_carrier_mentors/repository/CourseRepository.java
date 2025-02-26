package com.home.aircraft_carrier_mentors.repository;

import com.home.aircraft_carrier_mentors.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
}
