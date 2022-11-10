package com.belyaeva.services.impl;

import com.belyaeva.entity.Role;
import com.belyaeva.entity.User;
import com.belyaeva.repository.UserRepository;
import com.belyaeva.services.abstractions.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean saveUser(User user) {
        User userFromDB = (User) userRepository.findByPhone(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new Role("USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public User getTempUser(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal.equals("anonymousUser"))
            return null;
        else{
            UserDetails userDetails = (UserDetails) principal;
            return userRepository.findByPhone(userDetails.getUsername());
        }
    }
}
