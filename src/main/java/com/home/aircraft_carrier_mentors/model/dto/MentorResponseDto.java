package com.home.aircraft_carrier_mentors.model.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MentorResponseDto {
    @NonNull
    private Long id;

    @NonNull
    @Size(min = 1, max = 250)
    private String name;

    @NonNull
    private List<ContactDto> contactsDtoList;

    @NonNull
    private List<Long> internIds;

    @NonNull
    private Instant createdAt;


    public void setInternIds(List<Long> internIds) {
        this.internIds = internIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ContactDto> getContactsDtoList() {
        return contactsDtoList;
    }

    public void setContactsDtoList(List<ContactDto> contactsDtoList) {
        this.contactsDtoList = contactsDtoList;
    }

    public List<Long> getInternIds() {
        return internIds;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
