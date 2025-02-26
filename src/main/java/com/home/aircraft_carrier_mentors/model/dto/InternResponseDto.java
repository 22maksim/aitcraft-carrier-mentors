package com.home.aircraft_carrier_mentors.model.dto;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternResponseDto implements Serializable {
    private Long id;
    private String name;
    private List<ContactDto> contactDtoList;
    private List<Long> mentorIds;
    private Instant createdAt;

    public void setContactDtoList(List<ContactDto> contacts) {
        this.contactDtoList = contacts;
    }

    public void setMentorIds(List<Long> mentors) {
        this.mentorIds = mentors;
    }
}
