package com.belyaeva.services.impl;

import com.belyaeva.entity.Role;
import com.belyaeva.entity.User;
import com.belyaeva.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // FIX: Return Optional
        User user = userRepository.findByPhone(username);

        if (user == null)
            throw new UsernameNotFoundException("Пользователя с таким именем нет");

        Role[] userRoles = user.getRoles().toArray(Role[]::new);
        String [] userRolesStr = new String[userRoles.length];
        for (int i = 0; i < userRoles.length; i++) {
            userRolesStr[i] = userRoles[i].getName();
        }

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(userRolesStr)
                .build();

        return userDetails;
    }
}
