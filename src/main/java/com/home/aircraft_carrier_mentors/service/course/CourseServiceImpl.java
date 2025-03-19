package com.home.aircraft_carrier_mentors.service.course;

import com.home.aircraft_carrier_mentors.exception.MyNotFoundException;
import com.home.aircraft_carrier_mentors.mapper.CourseMapper;
import com.home.aircraft_carrier_mentors.model.Course;
import com.home.aircraft_carrier_mentors.model.UserOwner;
import com.home.aircraft_carrier_mentors.model.dto.CourseRequestDto;
import com.home.aircraft_carrier_mentors.model.dto.CourseResponseDto;
import com.home.aircraft_carrier_mentors.repository.CourseRepository;
import com.home.aircraft_carrier_mentors.repository.UserOwnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements  CourseService {
    private final CourseRepository courseRepository;
    private final UserOwnerRepository  userOwnerRepository;
    private final CourseMapper courseMapper;

    @Override
    @CachePut(value = "courseById", key = "#result.id")
    public CourseResponseDto createCourse(CourseRequestDto courseRequestDto) {
        Course course = new Course();
        course.setTitle(courseRequestDto.getTitle());
        course.setDescription(courseRequestDto.getDescription());

        UserOwner courseOwner = userOwnerRepository.findById(courseRequestDto.getUserOwnerId())
                .orElseThrow(() -> new MyNotFoundException("Owner not found. Id : " + courseRequestDto.getUserOwnerId()));
        course.setUserOwner(courseOwner);

        Course createdCourse = courseRepository.save(course);
        return courseMapper.courseToCourseResponseDto(createdCourse);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public CourseResponseDto getCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new MyNotFoundException("Course not found. Id : " + id));
        return courseMapper.courseToCourseResponseDto(course);
    }
}
