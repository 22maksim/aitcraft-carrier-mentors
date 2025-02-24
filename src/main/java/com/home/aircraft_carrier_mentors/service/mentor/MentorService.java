package com.home.aircraft_carrier_mentors.service.mentor;

import com.home.aircraft_carrier_mentors.model.dto.MentorRequestDto;
import com.home.aircraft_carrier_mentors.model.dto.MentorResponseDto;
import jakarta.validation.constraints.NotNull;

public interface MentorService {

    MentorResponseDto createMentor(MentorRequestDto requestDto);

    MentorResponseDto findMentorById(Long id);

    void deleteMentorById(@NotNull Long id);
}
