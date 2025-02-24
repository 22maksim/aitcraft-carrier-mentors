package com.home.aircraft_carrier_mentors.mapper;

import com.home.aircraft_carrier_mentors.model.Contact;
import com.home.aircraft_carrier_mentors.model.Intern;
import com.home.aircraft_carrier_mentors.model.Mentor;
import com.home.aircraft_carrier_mentors.model.dto.ContactDto;
import com.home.aircraft_carrier_mentors.model.dto.MentorRequestDto;
import com.home.aircraft_carrier_mentors.model.dto.MentorResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface MentorMapper {

    @Mapping(source = "contacts", target = "contactsDtoList", qualifiedByName = "contactListToContactDtoList")
    @Mapping(source = "interns", target = "internIds", qualifiedByName = "internToResponseDto")
    MentorResponseDto mentorToResponseDto(Mentor mentor);

    Mentor toMentor(MentorRequestDto mentorRequestDto);

    @Named("contactListToContactDtoList")
    default List<ContactDto> contactListToContactDtoList(List<Contact> contacts) {
        return (contacts != null) ? contacts.stream()
                .filter(Objects::nonNull)
                .map(contact -> ContactDto.builder().type(contact.getType()).value(contact.getValue()).build())
                .toList() : List.of();
    }

    @Named("internToResponseDto")
    default List<Long> internToResponseDto(List<Intern> interns) {
        return (interns != null) ? interns.stream()
                .filter(Objects::nonNull)
                .map(Intern::getId)
                .toList() : List.of();
    }
}
