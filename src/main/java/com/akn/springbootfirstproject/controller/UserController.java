package com.akn.springbootfirstproject.controller;

import com.akn.springbootfirstproject.model.ApplicationUser;
import com.akn.springbootfirstproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{Id}")
    public ApplicationUser getUser(@PathVariable("Id") long id){
        Optional<ApplicationUser> user = userService.getUser(id);
        return user.orElse(null);
    }

    @GetMapping
    public List<ApplicationUser> getUsers(){
        return userService.getUsers();
    }

    @PostMapping
    public ApplicationUser addUser(@RequestBody ApplicationUser applicationUser){
        return userService.addUser(applicationUser);
    }

    @PutMapping
    public ApplicationUser updateUser(@RequestBody ApplicationUser applicationUser){
        return userService.updateUser(applicationUser);
    }

    @DeleteMapping("/{Id}")
    public void deleteUser(@PathVariable("Id") long id){
        userService.deleteUser(id);
    }
}
