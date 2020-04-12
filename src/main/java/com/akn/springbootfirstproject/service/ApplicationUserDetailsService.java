package com.akn.springbootfirstproject.service;

import com.akn.springbootfirstproject.model.ApplicationUser;
import com.akn.springbootfirstproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser user = userRepository.getByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found");
        return user;
    }
}
