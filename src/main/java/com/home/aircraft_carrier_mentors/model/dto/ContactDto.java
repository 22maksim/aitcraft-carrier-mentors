package com.home.aircraft_carrier_mentors.model.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {
    private Long id;
    @NonNull
    @Size(min = 1, max = 50)
    private String type;
    @NonNull
    @Size(min = 1, max = 250)
    private String value;
}
