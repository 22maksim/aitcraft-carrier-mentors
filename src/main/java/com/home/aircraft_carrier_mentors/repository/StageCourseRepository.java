package com.home.aircraft_carrier_mentors.repository;

import com.home.aircraft_carrier_mentors.model.StageCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StageCourseRepository extends JpaRepository<StageCourse,Long> {
}
