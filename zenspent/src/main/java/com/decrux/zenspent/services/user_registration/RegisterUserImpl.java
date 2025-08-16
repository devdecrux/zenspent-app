package com.decrux.zenspent.services.user_registration;

import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.db.auth.ZSUserRole;
import com.decrux.zenspent.entities.dtos.RegisterUserDto;
import com.decrux.zenspent.repositories.UserRepository;
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
    public void registerUser(RegisterUserDto userDTO) {
        ZSUser user = new ZSUser();
        //user.setUsername(userDTO.username().trim());
        user.setPassword(this.passwordEncoder.encode(userDTO.password().trim()));
        user.setEmail(userDTO.email().trim());
        user.setFirstName(userDTO.firstName());
        user.setLastName(userDTO.lastName());

        ZSUserRole role = new ZSUserRole();
        role.setRole(ROLE_USER);
        user.setRoles(List.of(role));

        this.userRepository.saveAndFlush(user);
    }

}
