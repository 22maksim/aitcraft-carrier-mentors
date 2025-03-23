package com.home.aircraft_carrier_mentors.configuration.security.sertvice;

import com.home.aircraft_carrier_mentors.configuration.security.model.UserAccount;
import com.home.aircraft_carrier_mentors.configuration.security.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserAccountService implements UserDetailsService {
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount user = userAccountRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("SECURITY! I'm not found User: " +username));

        log.debug("SECURITY! I'm find and load User: {}", username);
        return user;
    }

    @Transactional
    public ResponseEntity<String> register(String username, String password) {
        if (!userAccountRepository.existsByUsername(username)) {
            UserAccount account = new UserAccount(username, passwordEncoder.encode(password));
            userAccountRepository.save(account);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Пользователь успешно создан");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Пользователь с таким именем уже существует!");
    }
}
