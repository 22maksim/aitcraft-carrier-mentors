package com.home.aircraft_carrier_mentors.service.mentor;

import com.home.aircraft_carrier_mentors.model.dto.MentorRequestDto;
import com.home.aircraft_carrier_mentors.model.dto.MentorResponseDto;

public interface MentorService {

    MentorResponseDto createMentor(MentorRequestDto requestDto);

}
