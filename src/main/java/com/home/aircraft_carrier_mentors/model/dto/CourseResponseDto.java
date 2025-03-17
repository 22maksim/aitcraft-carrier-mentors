package com.home.aircraft_carrier_mentors.model.dto;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDto implements Serializable {
    private Long id;
    private String title;
    private String description;
    private Long userOwnerId;
    private StructureCourseResponseDto structureCourse;
    private Instant updateAt;
    private Instant createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserOwnerId() {
        return userOwnerId;
    }

    public void setUserOwnerId(Long ownerId) {
        this.userOwnerId = ownerId;
    }

    public StructureCourseResponseDto getStructureCourse() {
        return structureCourse;
    }

    public void setStructureCourse(StructureCourseResponseDto structureCourse) {
        this.structureCourse = structureCourse;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
