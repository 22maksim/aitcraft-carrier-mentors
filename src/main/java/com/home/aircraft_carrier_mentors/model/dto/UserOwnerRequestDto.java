package com.home.aircraft_carrier_mentors.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserOwnerRequestDto {

    @NotNull
    @Size(min = 3, max = 255)
    private String username;

    private Long mentorId;
}
