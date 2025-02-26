package com.home.aircraft_carrier_mentors.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequestDto implements Serializable {
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private Long ownerId;
}
