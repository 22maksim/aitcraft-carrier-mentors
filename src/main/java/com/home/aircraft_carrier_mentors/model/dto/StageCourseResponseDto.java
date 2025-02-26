package com.home.aircraft_carrier_mentors.model.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record StageCourseResponseDto(
        Long id,
        String title,
        String description
) implements Serializable {
}
