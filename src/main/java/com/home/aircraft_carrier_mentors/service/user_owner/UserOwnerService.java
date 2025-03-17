package com.home.aircraft_carrier_mentors.service.user_owner;

import com.home.aircraft_carrier_mentors.model.dto.UserOwnerRequestDto;
import com.home.aircraft_carrier_mentors.model.dto.UserOwnerResponseDto;

public interface UserOwnerService {

    UserOwnerResponseDto createUserOwner(UserOwnerRequestDto requestDto);

    UserOwnerResponseDto getUserOwner(Long id);

    void deleteUserOwnerById(Long id);

}
