package com.home.aircraft_carrier_mentors.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Table(name = "courses")
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToOne(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private StructureCourse structureCourse;

    @ManyToOne
    @JoinColumn(name = "course_owner_id")
    private CourseOwner courseOwner;

    public CourseOwner getCourseOwner() {
        return courseOwner;
    }

    public void setCourseOwner(CourseOwner courseOwner) {
        this.courseOwner = courseOwner;
    }

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

    public StructureCourse getStructureCourse() {
        return structureCourse;
    }

    public void setStructureCourse(StructureCourse structureCourse) {
        this.structureCourse = structureCourse;
    }
}
