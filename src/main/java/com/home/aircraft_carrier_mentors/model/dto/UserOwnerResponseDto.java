package com.home.aircraft_carrier_mentors.model.dto;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserOwnerResponseDto {
    private Long id;
    private String username;
    private List<Long> courseIds;
    private Long mentorId;
    private Instant createdAt;
    private Instant timeOfVisit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Long> getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(List<Long> courseIds) {
        this.courseIds = courseIds;
    }

    public Long getMentorId() {
        return mentorId;
    }

    public void setMentorId(Long mentorId) {
        this.mentorId = mentorId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getTimeOfVisit() {
        return timeOfVisit;
    }

    public void setTimeOfVisit(Instant timeOfVisit) {
        this.timeOfVisit = timeOfVisit;
    }
}
