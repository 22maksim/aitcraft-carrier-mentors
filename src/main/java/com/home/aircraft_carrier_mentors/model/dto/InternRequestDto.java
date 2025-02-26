package com.home.aircraft_carrier_mentors.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InternRequestDto implements Serializable {
    @NotNull
    @Size(min = 1, max = 250)
    private String name;
    private List<ContactDto> contacts;
}
