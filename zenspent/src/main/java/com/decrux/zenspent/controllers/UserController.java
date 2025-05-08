package com.decrux.zenspent.controllers;

import com.decrux.zenspent.entities.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.RegisterUserDTO;
import com.decrux.zenspent.entities.dtos.ZSUserDTO;
import com.decrux.zenspent.services.RegisterUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final RegisterUser registerUser;

    @PostMapping("/register")
    public void registerUser(@RequestBody RegisterUserDTO registerUserDTO) {
        this.registerUser.registerUser(registerUserDTO);
    }

    @GetMapping
    public ZSUserDTO retrieveUserData(@AuthenticationPrincipal ZSUser user) {
        return ZSUserDTO.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

}
