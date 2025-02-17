package com.home.aircraft_carrier_mentors.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MentorRequestDto {
    private Long id;

    @NotNull
    private String name;
    @NotNull
    private List<ContactDto> contacts;
}
