package com.home.aircraft_carrier_mentors.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MentorRequestDto implements Serializable {
    private Long id;

    @NotNull
    private String name;

    private Long userOwnerId;

    @NotNull
    private List<ContactDto> contacts;
}
