package com.home.aircraft_carrier_mentors.model.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MentorResponseDto {
    @NonNull
    private Long id;

    @NonNull
    @Size(min = 1, max = 250)
    private String name;

    private List<ContactDto> contact;

    private List<Long> internIds;

    public void setContact(@NonNull List<ContactDto> contact) {
        this.contact = contact;
    }

    public void setInternIds(@NonNull List<Long> interns) {
        this.internIds = interns;
    }
}
