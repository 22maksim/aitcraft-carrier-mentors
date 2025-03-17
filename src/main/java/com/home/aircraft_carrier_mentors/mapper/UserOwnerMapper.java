package com.home.aircraft_carrier_mentors.mapper;

import com.home.aircraft_carrier_mentors.model.Course;
import com.home.aircraft_carrier_mentors.model.UserOwner;
import com.home.aircraft_carrier_mentors.model.dto.UserOwnerResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UserOwnerMapper {

    @Mapping(target = "mentorId", source = "mentor.id")
    @Mapping(target = "courseIds", source = "courses", qualifiedByName = "toCourseIds")
    UserOwnerResponseDto toResponseDto(UserOwner userOwner);

    @Named(value = "toCourseIds")
    default List<Long> toCourseIds(List<Course> courses) {
        return courses != null ? courses.stream().map(Course::getId).toList()  : Collections.emptyList();
    }
}
