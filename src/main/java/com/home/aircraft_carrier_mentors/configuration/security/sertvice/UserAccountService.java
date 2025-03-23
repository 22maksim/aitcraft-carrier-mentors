package com.home.aircraft_carrier_mentors.configuration.security.sertvice;

import com.home.aircraft_carrier_mentors.configuration.security.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserAccountService implements UserDetailsService {
    private final UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userAccountRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("SECURITY! I'm not found User: " +username));

        log.debug("SECURITY! I'm find and load User: {}", username);
        return user;
    }
}
