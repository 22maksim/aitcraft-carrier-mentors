package com.home.aircraft_carrier_mentors.configuration.security.model;

import com.home.aircraft_carrier_mentors.model.dto.UserOwnerResponseDto;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountResponseDto {
    @NonNull
    private String token;
    @NonNull
    private String username;
    @NonNull
    private UserOwnerResponseDto user;
}
