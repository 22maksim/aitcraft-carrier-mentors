package com.home.aircraft_carrier_mentors.configuration.security.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user_account")
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount implements UserDetails {
    @Id
    @Size(min = 5, max = 55)
    private String username;

    @Column(name = "password", nullable = false)
    @Size(min = 8, max = 100)
    private String password;

    @Column(name = "user_owner", nullable = false, unique = true)
    private Long userOwner;

    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "username"))
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private List<UserRole> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getValue()))
                .toList();
    }

    @Override
    public String getPassword() {
        return username;
    }

    @Override
    public String getUsername() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
