package com.home.aircraft_carrier_mentors.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StructureCourseResponseDto {
    private String title;
    private Instant updatedAt;
    private Instant createdAt;
}
