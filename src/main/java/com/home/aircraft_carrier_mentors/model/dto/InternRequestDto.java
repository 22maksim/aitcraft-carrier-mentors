package com.home.aircraft_carrier_mentors.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InternRequestDto {
    @NotNull
    @Size(min = 1, max = 250)
    private String name;
    private List<ContactDto> contacts;
}
