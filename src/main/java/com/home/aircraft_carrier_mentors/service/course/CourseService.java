package com.home.aircraft_carrier_mentors.service.course;

import com.home.aircraft_carrier_mentors.model.dto.CourseRequestDto;
import com.home.aircraft_carrier_mentors.model.dto.CourseResponseDto;
import jakarta.validation.constraints.NotNull;

public interface CourseService {

    CourseResponseDto createCourse(CourseRequestDto courseRequestDto);

    void deleteCourse(Long id);

    CourseResponseDto getCourse(@NotNull Long id);
}
