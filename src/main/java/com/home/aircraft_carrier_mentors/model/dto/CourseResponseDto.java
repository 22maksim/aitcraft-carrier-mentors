package com.home.aircraft_carrier_mentors.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDto {
    private Long id;
    private String title;
    private String description;
    private Long ownerId;
    private Long structureCourseId;
    private Instant updateAt;
    private Instant createdAt;
}
