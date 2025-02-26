package com.home.aircraft_carrier_mentors.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StageCourseRequestDto  implements Serializable {
    private String title;
    private String description;
}
