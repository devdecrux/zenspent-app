package com.decrux.zenspent.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForwardToVueAppController {

    @GetMapping(value = {"/frontend", "/frontend/{x:[\\w\\-]+}", "/frontend/"})
    public String forwardToVueApp() {
        return "forward:/frontend/index.html";
    }

}
