package com.home.aircraft_carrier_mentors.configuration.security.repository;

import com.home.aircraft_carrier_mentors.configuration.security.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
    boolean existsByUsername(String username);

    Optional<UserAccount> findByUsername(String username);
}
