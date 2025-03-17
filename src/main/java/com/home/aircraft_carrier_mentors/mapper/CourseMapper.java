package com.home.aircraft_carrier_mentors.mapper;

import com.home.aircraft_carrier_mentors.model.Course;
import com.home.aircraft_carrier_mentors.model.dto.CourseResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseMapper {

    @Mapping(target = "userOwnerId", source = "userOwner.id")
    CourseResponseDto courseToCourseResponseDto(Course course);

}
