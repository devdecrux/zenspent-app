package com.decrux.zenspent.entities.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record CustomUserPrincipal(User user) implements UserDetails {

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<UserRole> roles = new HashSet<>(this.user.getRoles());
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
                .collect(Collectors.toSet());
    }

}
