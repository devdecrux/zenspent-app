package com.decrux.zenspent.controllers;

import com.decrux.zenspent.entities.dtos.RegisterUserDTO;
import com.decrux.zenspent.services.RegisterUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final RegisterUser registerUser;

    @PostMapping("/register")
    public void registerUser(@RequestBody RegisterUserDTO registerUserDTO) {
        this.registerUser.registerUser(registerUserDTO);
    }

}
