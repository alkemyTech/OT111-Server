package com.alkemy.ong.auth.service;

import com.alkemy.ong.model.entity.FoundUserEntity;
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

        // === Testeo de USER ENTITY ===
        // TODO: UserEntity foundUser = UserRepository.findByEmail()
        FoundUserEntity foundUser = new FoundUserEntity();
        foundUser.setEmail("user");
        foundUser.setPassword("$2a$12$SseiD77XMlFI9uR.4vfAF.a4okOn/IZXvo9X7UkS8D5l5nAAiW61y");

        // TODO: === Exception IF NOT FOUND ===
//         if (foundUser == null) {
//              throw new UsernameNotFoundException("Username: " + username + " -> NOT FOUND");
//         }

        // === Set Spring Security USER ===
        return new User(
                foundUser.getEmail(),
                foundUser.getPassword(),
                Collections.emptyList() // TODO: Roles
        );

    }
}


