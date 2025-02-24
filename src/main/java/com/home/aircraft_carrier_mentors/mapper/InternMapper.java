package com.home.aircraft_carrier_mentors.mapper;

import com.home.aircraft_carrier_mentors.model.Contact;
import com.home.aircraft_carrier_mentors.model.Intern;
import com.home.aircraft_carrier_mentors.model.Mentor;
import com.home.aircraft_carrier_mentors.model.dto.ContactDto;
import com.home.aircraft_carrier_mentors.model.dto.InternResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InternMapper {

    @Mapping(source = "mentors", target = "mentorIds", qualifiedByName = "mentorToMentorResponseDtoList")
    @Mapping(source = "contacts", target = "contactDtoList", qualifiedByName = "contactToContactDtoList")
    InternResponseDto internToResponseDto(Intern intern);

    @Named("contactToContactDtoList")
    default List<ContactDto> contactToContactDtoList(List<Contact> contacts) {
        return (contacts != null) ? contacts.stream()
                .filter(Objects::nonNull)
                .map(contact -> ContactDto.builder().value(contact.getValue()).type(contact.getType()).build())
                .toList() : Collections.emptyList();
    }

    @Named("mentorToMentorResponseDtoList")
    default List<Long> mentorToMentorResponseDtoList(List<Mentor> mentors) {
        return (mentors != null) ? mentors.stream()
                .filter(Objects::nonNull)
                .map(Mentor::getId)
                .toList() : Collections.emptyList();
    }
}
