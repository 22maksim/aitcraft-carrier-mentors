package com.home.aircraft_carrier_mentors.configuration.security.sertvice;

import com.home.aircraft_carrier_mentors.configuration.security.model.JWTUtils;
import com.home.aircraft_carrier_mentors.configuration.security.model.UserAccount;
import com.home.aircraft_carrier_mentors.configuration.security.model.UserAccountDto;
import com.home.aircraft_carrier_mentors.configuration.security.model.UserRole;
import com.home.aircraft_carrier_mentors.configuration.security.repository.UserAccountRepository;
import com.home.aircraft_carrier_mentors.model.dto.UserOwnerRequestDto;
import com.home.aircraft_carrier_mentors.service.user_owner.UserOwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserAccountService implements UserDetailsService {
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserOwnerService userOwnerServiceImpl;
    private final AuthenticationManager authenticationManager;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount user = userAccountRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("SECURITY! I'm not found User: " +username));

        log.debug("SECURITY! I'm find and load User: {}", username);
        return user;
    }

    @Transactional
    public ResponseEntity<String> register(UserAccountDto registerDto) {
        if (!userAccountRepository.existsByUsername(registerDto.getUsername())) {
            UserAccount account = new UserAccount(
                    registerDto.getUsername(), passwordEncoder.encode(registerDto.getPassword()));
            userAccountRepository.save(account);
            userOwnerServiceImpl.createUserOwner(
                    UserOwnerRequestDto.builder().username(registerDto.getUsername()).build());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Пользователь успешно создан");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Пользователь с таким именем уже существует!");
    }

    public ResponseEntity<String> login(UserAccountDto userLoginDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLoginDto.getUsername(), userLoginDto.getPassword()
                    )
            );

            String token = JWTUtils.generateToken(
                    userLoginDto.getUsername(),
                    Map.of("sub", userLoginDto.getUsername(), "role", UserRole.USER.getValue())
            );
            return ResponseEntity.ok(token);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Неверное имя пользователя или пароль");
        }
    }
}
