package com.home.aircraft_carrier_mentors.configuration.security.sertvice;

import com.home.aircraft_carrier_mentors.configuration.security.model.JWTUtils;
import com.home.aircraft_carrier_mentors.configuration.security.model.UserAccount;
import com.home.aircraft_carrier_mentors.configuration.security.model.UserAccountDto;
import com.home.aircraft_carrier_mentors.configuration.security.model.UserRole;
import com.home.aircraft_carrier_mentors.configuration.security.repository.UserAccountRepository;
import com.home.aircraft_carrier_mentors.model.dto.UserOwnerRequestDto;
import com.home.aircraft_carrier_mentors.service.user_owner.UserOwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
@Service
public class UserAccountService implements UserDetailsService {
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserOwnerService userOwnerServiceImpl;
    private final Supplier<UserAccountService> userAccountServiceSupplier;

    public UserAccountService(
            UserAccountRepository userAccountRepository,
            PasswordEncoder passwordEncoder,
            UserOwnerService userOwnerServiceImpl,
            BeanFactory beanFactory) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
        this.userOwnerServiceImpl = userOwnerServiceImpl;
        this.userAccountServiceSupplier = () -> beanFactory.getBean(UserAccountService.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount user = userAccountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("SECURITY! I'm not found User: " + username));

        log.debug("SECURITY! I'm find and load User: {}", username);
        return user;
    }

    public void registerUser(UserAccountDto registerDto) {
        userAccountServiceSupplier.get().registerUserWithRole(registerDto, UserRole.USER);
        log.info("Пользователь {} успешно зарегистрирован", registerDto.getUsername());
    }

    public void registerAdmin(UserAccountDto registerDto) {
        userAccountServiceSupplier.get().registerUserWithRole(registerDto, UserRole.ADMIN);
        log.info("Администратор успешно зарегистрирован. Username {}", registerDto.getUsername());
    }

    @Transactional
    public void registerUserWithRole(UserAccountDto registerDto, UserRole role) {
        if (getUserAccountByUsername(registerDto.getUsername()).isPresent()) {
            log.error("Ошибка при регистрации: пользователь {} уже существует", registerDto.getUsername());
            throw new IllegalStateException("Такой пользователь существует");
        }

        UserAccount account = new UserAccount(
                registerDto.getUsername(), passwordEncoder.encode(registerDto.getPassword()), role);
        userAccountRepository.save(account);
        userOwnerServiceImpl.createUserOwner(
                UserOwnerRequestDto.builder().username(registerDto.getUsername()).build());
    }

    @Transactional
    public String login(UserAccountDto userLoginDto) {
        UserAccount account = getUserAccountByUsername(userLoginDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден: " + userLoginDto.getUsername()));

        if (!passwordEncoder.matches(userLoginDto.getPassword(), account.getPassword())) {
            log.warn("Неудачная попытка входа для {}", userLoginDto.getUsername());
            throw new BadCredentialsException("Неверный пароль");
        }

        String role = account.getRoles().contains(UserRole.ADMIN) ? UserRole.ADMIN.getValue() : UserRole.USER.getValue();

        log.info("Пользователь {} вошел в систему", userLoginDto.getUsername());
        return JWTUtils.generateToken(
                account.getUsername(),
                Map.of("sub", account.getUsername(), "role", role)
        );
    }

    private Optional<UserAccount> getUserAccountByUsername(String username) {
        return userAccountRepository.findByUsername(username);
    }
}
