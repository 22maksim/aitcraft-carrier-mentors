package com.home.aircraft_carrier_mentors.mapper.custom;

import com.home.aircraft_carrier_mentors.mapper.StageCourseMapper;
import com.home.aircraft_carrier_mentors.model.StructureCourse;
import com.home.aircraft_carrier_mentors.model.dto.StageCourseResponseDto;
import com.home.aircraft_carrier_mentors.model.dto.StructureCourseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CustomStructureCourseMapper {
    private final StageCourseMapper stageCourseMapper;

    public StructureCourseResponseDto toResponseDto(StructureCourse structureCourse) {
        List<StageCourseResponseDto> stageCourseResponseDtoList  = new ArrayList<>();
        if (structureCourse.getStages() != null || !structureCourse.getStages().isEmpty()) {
            stageCourseResponseDtoList = structureCourse.getStages().stream()
                    .filter(Objects::nonNull)
                    .map(stageCourseMapper::toResponseDto)
                    .toList();
        }

        return StructureCourseResponseDto.builder()
                .id(structureCourse.getId())
                .courseId(structureCourse.getCourse().getId())
                .createdAt(structureCourse.getCreatedAt())
                .updatedAt(structureCourse.getUpdatedAt())
                .stages(stageCourseResponseDtoList)
                .build();
    }

}
