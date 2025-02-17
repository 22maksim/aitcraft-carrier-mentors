package com.home.aircraft_carrier_mentors.model.dto;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternResponseDto {
    @NonNull
    private Long id;
    @NonNull
    private String name;
    private List<ContactDto> contactDtoList;
    private List<Long> mentorIds;
    @NonNull
    private Instant createdAt;

    public void setContactDtoList(@NonNull List<ContactDto> contacts) {
        this.contactDtoList = contacts;
    }

    public void setMentorIds(@NonNull List<Long> mentors) {
        this.mentorIds = mentors;
    }
}
