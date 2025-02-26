package com.home.aircraft_carrier_mentors.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "user_description")
public class UserOwner {
  @Id
  private Long id;

  @Column(name = "username")
  private String username;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "course_owner_id")
  private CourseOwner courseOwner; // Он нужен и должен быть владельцем курсов, и может быть только он владельцем, если есть роль владельца курсов, то он интерном быть уже не может

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "mentor_id")
  private Mentor mentor;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "intern_id")
  private Intern intern;

  @Column(name = "created_at", updatable = false)
  @CreationTimestamp
  private Instant createdAt;

  @Column(name = "time_of_visit")
  @UpdateTimestamp
  private Instant timeOfVisit;
}