package com.decrux.zenspent.config.security;

import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.exceptions.UserNotFoundException;
import com.decrux.zenspent.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ZSUser user = this.userRepository.findUserByEmail(email);
        if (null == user) {
            throw new UserNotFoundException("User with email " + email + " not found");
        }
        return user;
    }

}
