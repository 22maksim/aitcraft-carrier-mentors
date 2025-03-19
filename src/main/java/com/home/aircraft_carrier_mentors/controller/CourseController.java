package com.home.aircraft_carrier_mentors.controller;

import com.home.aircraft_carrier_mentors.model.dto.CourseRequestDto;
import com.home.aircraft_carrier_mentors.model.dto.CourseResponseDto;
import com.home.aircraft_carrier_mentors.service.course.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/courses")
@Validated
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseServiceImpl;

    @PostMapping
    public CourseResponseDto createCourse(@RequestBody @NotNull @Valid CourseRequestDto courseRequestDto) {
        return courseServiceImpl.createCourse(courseRequestDto);
    }

    @GetMapping("{id}")
    @Cacheable(value = "courseById", key = "#id")
    public CourseResponseDto getCourse(@PathVariable @NotNull Long id) {
        return courseServiceImpl.getCourse(id);
    }

    @CacheEvict(value = "courseById", key = "#id")
    @DeleteMapping("{id}")
    public void deleteCourse(@PathVariable @NotNull Long id) {
        courseServiceImpl.deleteCourse(id);
    }
}
