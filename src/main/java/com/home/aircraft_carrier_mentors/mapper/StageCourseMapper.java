package com.home.aircraft_carrier_mentors.mapper;

import com.home.aircraft_carrier_mentors.model.StageCourse;
import com.home.aircraft_carrier_mentors.model.dto.StageCourseResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StageCourseMapper {

    StageCourseResponseDto  toResponseDto(StageCourse stageCourse);

}
