package com.akn.springbootfirstproject.service;

import com.akn.springbootfirstproject.aspect.TrackTime;
import com.akn.springbootfirstproject.model.ApplicationUser;
import com.akn.springbootfirstproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<ApplicationUser> getUser(long id){
        return userRepository.findById(id);
    }

    @TrackTime
    public List<ApplicationUser> getUsers(){
        return userRepository.findAll();
    }

    public ApplicationUser addUser(ApplicationUser applicationUser){
        String encodedPassword = passwordEncoder.encode(applicationUser.getPassword());
        applicationUser.setPassword(encodedPassword);
        return userRepository.save(applicationUser);
    }

    public ApplicationUser updateUser(ApplicationUser applicationUser){
        return userRepository.save(applicationUser);
    }

    public void deleteUser(long id){
        userRepository.deleteById(id);
    }

}
