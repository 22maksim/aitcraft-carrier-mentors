package com.home.aircraft_carrier_mentors.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "course_owner")
public class CourseOwner {

    @Id
    private Long id;

    @OneToMany(mappedBy = "courseOwner")
    private List<Course> courses;

    @OneToOne(mappedBy = "courseOwner")
    private UserOwner userOwner;
}