package com.decrux.zenspent.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/internal")
public class InternalVueAppController {

    @GetMapping("/csrf-token")
    public void getCsrfToken() {
        // This endpoint can be used to retrieve the CSRF token for Vue.js applications in development mode only.
        // TODO active only in development mode
    }
}
