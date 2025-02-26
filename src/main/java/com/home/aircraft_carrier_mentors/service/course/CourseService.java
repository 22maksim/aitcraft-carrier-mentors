package com.home.aircraft_carrier_mentors.service.course;

import com.home.aircraft_carrier_mentors.model.dto.CourseRequestDto;
import com.home.aircraft_carrier_mentors.model.dto.CourseResponseDto;

public interface CourseService {

    CourseResponseDto createCourse(CourseRequestDto courseRequestDto);

}
