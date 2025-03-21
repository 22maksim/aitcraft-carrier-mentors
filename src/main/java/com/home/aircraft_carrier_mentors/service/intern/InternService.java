package com.home.aircraft_carrier_mentors.service.intern;

import com.home.aircraft_carrier_mentors.model.dto.ContactDto;
import com.home.aircraft_carrier_mentors.model.dto.InternResponseDto;
import com.home.aircraft_carrier_mentors.model.dto.InternRequestDto;
import com.home.aircraft_carrier_mentors.model.dto.MentorRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface InternService {

    InternResponseDto createIntern(InternRequestDto requestDto);

    InternResponseDto addMentorFromIntern(Long id, @NotNull MentorRequestDto requestDto);

    InternResponseDto addContact(@NotNull @Valid ContactDto contactDto, Long id);

    InternResponseDto getInternById(@NotNull Long id);
}
