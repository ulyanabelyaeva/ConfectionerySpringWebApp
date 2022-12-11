package com.belyaeva.model.services.impl;

import com.belyaeva.model.entity.RoleEntity;
import com.belyaeva.model.entity.UserEntity;
import com.belyaeva.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // FIX: Return Optional
        UserEntity user = userRepository.findByPhone(username);

        if (user == null)
            throw new UsernameNotFoundException("Пользователя с таким именем нет");

        RoleEntity[] userRoleEntities = user.getRoles().toArray(RoleEntity[]::new);
        String [] userRolesStr = new String[userRoleEntities.length];
        for (int i = 0; i < userRoleEntities.length; i++) {
            userRolesStr[i] = userRoleEntities[i].getName();
        }

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(userRolesStr)
                .build();

        return userDetails;
    }
}
