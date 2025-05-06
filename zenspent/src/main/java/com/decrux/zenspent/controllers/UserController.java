package com.decrux.zenspent.controllers;

import com.decrux.zenspent.entities.dtos.RegisterUserDTO;
import com.decrux.zenspent.entities.security.ZSUser;
import com.decrux.zenspent.services.RegisterUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
    public Object retrieveUserData(@AuthenticationPrincipal Principal principal) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ZSUser authenticatedUser = (ZSUser) auth.getPrincipal();
        return authenticatedUser;
    }

}
