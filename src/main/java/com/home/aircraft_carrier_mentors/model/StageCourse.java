package com.home.aircraft_carrier_mentors.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stage_courses")
@NoArgsConstructor
@AllArgsConstructor
public class StageCourse {
    @Id
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "descri[tion", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_structure_id")
    private StructureCourse structureCourse;

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
