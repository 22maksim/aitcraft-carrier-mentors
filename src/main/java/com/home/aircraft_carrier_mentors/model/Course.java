package com.home.aircraft_carrier_mentors.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
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
    private CourseStructure courseStructure;

    @ManyToOne
    @JoinColumn(name = "course_owner_id")
    private CourseOwner courseOwner;

    public CourseOwner getCourseOwner() {
        return courseOwner;
    }

    public void setCourseOwner(CourseOwner courseOwner) {
        this.courseOwner = courseOwner;
    }
}
