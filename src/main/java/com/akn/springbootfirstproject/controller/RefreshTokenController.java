package com.akn.springbootfirstproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/refresh")
public class RefreshTokenController {

    @GetMapping
    public String refresh() {
        return "Token refreshed";
    }
}
