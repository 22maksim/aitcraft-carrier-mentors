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

    @Column(name = "account_non_expired", nullable = false)
    private boolean accountNonExpired = true;

    @Column(name = "account_non_locked", nullable = false)
    private boolean accountNonLocked = true;

    @Override
    public String getPassword() {
        return username;
    }

    @Override
    public String getUsername() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    // На проекте использую для управления блокировкой пользователя, например, после нескольких неудачных попыток входа5
    // или по решению администратора. Кол-во попыток еще не реализовывал. А для админа надо добавить endpoint типа
    // blockUser и еще unLockUser, где буду устанавливать значение false и true соответственно
    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }
}
