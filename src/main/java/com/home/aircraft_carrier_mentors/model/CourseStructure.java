package com.home.aircraft_carrier_mentors.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "course_structures")
public class CourseStructure {
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "courseStructure", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StageCourse> stages;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "created_at")
    private Instant createdAt;
}
