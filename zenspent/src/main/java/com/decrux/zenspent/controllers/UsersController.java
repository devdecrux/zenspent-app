package com.decrux.zenspent.controllers;

import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.RegisterUserDto;
import com.decrux.zenspent.entities.dtos.ZSUserDto;
import com.decrux.zenspent.services.user_registration.RegisterUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UsersController {

    private final RegisterUser registerUser;

    @PostMapping("/register")
    public void registerUser(@RequestBody RegisterUserDto registerUserDTO) {
        this.registerUser.registerUser(registerUserDTO);
    }

    @GetMapping
    public ZSUserDto retrieveUserData(@AuthenticationPrincipal ZSUser user) {
        return ZSUserDto.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

}
