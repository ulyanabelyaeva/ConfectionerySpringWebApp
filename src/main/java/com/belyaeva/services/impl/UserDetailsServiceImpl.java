package com.belyaeva.services.impl;

import com.belyaeva.entity.Role;
import com.belyaeva.entity.User;
import com.belyaeva.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByPhone(username);

        if (user == null)
            throw new UsernameNotFoundException("Пользователя с таким именем нет");

        /*UserDetails userDetails = User.builder()
                .phone(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();*/

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

/*        Iterator<Role> iter = user.getRoles().iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next().getName());
        }*/

        return userDetails;
    }
}
