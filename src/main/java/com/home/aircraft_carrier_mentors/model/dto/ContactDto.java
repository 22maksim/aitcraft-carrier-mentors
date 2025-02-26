package com.home.aircraft_carrier_mentors.model.dto;

import com.home.aircraft_carrier_mentors.model.enums.ContactType;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.NonNull;

import java.io.Serializable;

@Builder
public record ContactDto (
    ContactType type,
    String value
) implements Serializable {
}
