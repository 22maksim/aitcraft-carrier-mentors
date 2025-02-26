package com.home.aircraft_carrier_mentors.service.course;

import com.home.aircraft_carrier_mentors.exception.MyNotFoundException;
import com.home.aircraft_carrier_mentors.mapper.CourseMapper;
import com.home.aircraft_carrier_mentors.model.Course;
import com.home.aircraft_carrier_mentors.model.CourseOwner;
import com.home.aircraft_carrier_mentors.model.dto.CourseRequestDto;
import com.home.aircraft_carrier_mentors.model.dto.CourseResponseDto;
import com.home.aircraft_carrier_mentors.repository.CourseOwnerRepository;
import com.home.aircraft_carrier_mentors.repository.CourseRepository;
import com.home.aircraft_carrier_mentors.repository.UserOwnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements  CourseService {
    private final CourseRepository courseRepository;
    private final UserOwnerRepository  userOwnerRepository;
    private final CourseOwnerRepository courseOwnerRepository;
    private final CourseMapper courseMapper;

    @Override
    public CourseResponseDto createCourse(CourseRequestDto courseRequestDto) {
        Course course = new Course();
        course.setTitle(courseRequestDto.getTitle());
        course.setDescription(courseRequestDto.getDescription());

        CourseOwner courseOwner = courseOwnerRepository.findById(courseRequestDto.getOwnerId())
                .orElseThrow(() -> new MyNotFoundException("Owner not found. Id : " + courseRequestDto.getOwnerId()));
        course.setCourseOwner(courseOwner);

        Course createdCourse = courseRepository.save(course);
        return courseMapper.courseToCourseResponseDto(createdCourse);
    }
}
