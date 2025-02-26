package com.home.aircraft_carrier_mentors.model.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MentorResponseDto  implements Serializable {
    private Long id;
    private String name;
    private List<ContactDto> contactsDtoList;
    private List<Long> internIds;
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
