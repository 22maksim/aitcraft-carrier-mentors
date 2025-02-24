package com.home.aircraft_carrier_mentors.model.dto;

import com.home.aircraft_carrier_mentors.model.enums.ContactType;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record ContactDto (
    @NonNull
    ContactType type,

    @NonNull
    @Size(min = 1, max = 250)
    String value
) {
}
