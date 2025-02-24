package com.home.aircraft_carrier_mentors.model;

import jakarta.persistence.*;

@Entity
@Table(name = "stage_courses")
public class StageCourse {
    @Id
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "descri[tion", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_structure_id")
    private CourseStructure courseStructure;
}
