package com.home.aircraft_carrier_mentors.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@Table(name = "course_owner")
@NoArgsConstructor
@AllArgsConstructor
public class CourseOwner {

    @Id
    private Long id;

    @OneToMany(mappedBy = "courseOwner")
    private List<Course> courses;

    @OneToOne(mappedBy = "courseOwner")
    private UserOwner userOwner;
}