package com.home.aircraft_carrier_mentors.repository;

import com.home.aircraft_carrier_mentors.model.StructureCourse;
import com.home.aircraft_carrier_mentors.model.dto.StructureCourseResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StructureCourseRepository extends JpaRepository<StructureCourse,Long> {

    @Query(value = """
        SELECT * FROM structures_course WHERE course_id = ?1
    """, nativeQuery = true)
    List<StructureCourse> findAllByCourseId(Long idCourse);
}
