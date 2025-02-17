package com.home.aircraft_carrier_mentors.model;

import com.home.aircraft_carrier_mentors.model.enums.ContactType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@Table(name = "contact")
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ContactType type;

    @Column(name = "value", nullable = false)
    private String value;
}
