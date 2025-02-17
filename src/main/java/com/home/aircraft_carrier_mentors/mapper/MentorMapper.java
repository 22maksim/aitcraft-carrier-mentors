package com.home.aircraft_carrier_mentors.mapper;

import com.home.aircraft_carrier_mentors.model.Mentor;
import com.home.aircraft_carrier_mentors.model.dto.MentorRequestDto;
import com.home.aircraft_carrier_mentors.model.dto.MentorResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = {MentorMapper.class})
public interface MentorMapper {


    MentorResponseDto toMentorResponseDto(Mentor mentor);

    @Mapping(target = "contacts", ignore = true)
    Mentor toMentor(MentorRequestDto mentorRequestDto);

}
