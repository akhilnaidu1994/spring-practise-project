package com.akn.springbootfirstproject.controller;

import com.akn.springbootfirstproject.model.ApplicationUser;
import com.akn.springbootfirstproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest")
public class SignupController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public void signup(@RequestBody ApplicationUser user) {
        userService.addUser(user);
    }
}
