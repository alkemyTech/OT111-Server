package com.alkemy.ong.auth.service;

import com.alkemy.ong.auth.FoundUserEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // SpringSecurity will take care of password, we must match Username with JPA
        // UserEntity foundUser = FindByUsername() - Repository
        FoundUserEntity foundUser = new FoundUserEntity();
        foundUser.setUsername("email");
        foundUser.setPassword("password");

        // IF No User: Exception
        // if (foundUser == null) {
        //      throw new UsernameNotFoundException("Username: " + username + " -> NOT FOUND");
        // }

        // IF User: Creamos un Nuevo User Object con datos del UserEncontrado.
        return new User(
                foundUser.getUsername(),
                foundUser.getPassword(),
                Collections.emptyList() // Roles
        );
    }
}


