package com.home.aircraft_carrier_mentors.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StructureCourseResponseDto implements Serializable {
    private Long id;
    private Long courseId;
    private List<StageCourseResponseDto> stages;
    private Instant updatedAt;
    private Instant createdAt;

    public void setId(Long id) {
        this.id = id;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public void setStages(List<StageCourseResponseDto> stages) {
        this.stages = stages;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
