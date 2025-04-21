package com.decrux.zenspent.services.impl;

import com.decrux.zenspent.entities.dtos.RegisterUserDTO;
import com.decrux.zenspent.entities.security.User;
import com.decrux.zenspent.entities.security.UserRole;
import com.decrux.zenspent.repositories.UserRepository;
import com.decrux.zenspent.services.RegisterUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterUserImpl implements RegisterUser {

    private static final String ROLE_USER = "USER";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void registerUser(RegisterUserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.username());
        user.setPassword(this.passwordEncoder.encode(userDTO.password()));
        user.setEmail(userDTO.email());

        UserRole role = new UserRole();
        role.setRole(ROLE_USER);
        user.setRoles(List.of(role));

        this.userRepository.saveAndFlush(user);
    }

}
