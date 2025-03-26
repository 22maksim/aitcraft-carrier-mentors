package com.home.aircraft_carrier_mentors.service.user_owner;

import com.home.aircraft_carrier_mentors.model.dto.MentorRequestDto;
import com.home.aircraft_carrier_mentors.model.dto.UserOwnerRequestDto;
import com.home.aircraft_carrier_mentors.model.dto.UserOwnerResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface UserOwnerService {

    UserOwnerResponseDto createUserOwner(UserOwnerRequestDto requestDto);

    UserOwnerResponseDto getUserOwner(Long id);

    void deleteUserOwnerById(Long id);

    UserOwnerResponseDto becomeMentor(MentorRequestDto mentorRequestDto, Long userOwnerId);
}
