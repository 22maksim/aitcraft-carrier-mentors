package com.home.aircraft_carrier_mentors.configuration.security.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountDto implements Serializable {
    @NotNull
    @Size(min = 5, max = 55)
    private String username;
    @NotNull
    @Size(min = 8, max = 100)
    private String password;
}
