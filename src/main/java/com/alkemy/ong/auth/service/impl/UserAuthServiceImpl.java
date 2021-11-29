package com.alkemy.ong.auth.service.impl;

import com.alkemy.ong.auth.service.UserAuthService;
import com.alkemy.ong.model.dto.AuthenticationRequest;
import com.alkemy.ong.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    AuthenticationManager authManager;

    @Override
    public UserDTO loginAttempt(AuthenticationRequest authenticationRequest) throws Exception {
        UserDetails userDetails;

        try {
            UsernamePasswordAuthenticationToken newTry = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            );
            Authentication auth = authManager.authenticate(newTry);

            SecurityContextHolder.getContext().setAuthentication(auth);

            // Deberia Agarrarlo del Context o del Auth?
            userDetails = (UserDetails) auth.getPrincipal();

        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        UserDTO foundUser = new UserDTO();
        foundUser.setEmail(userDetails.getUsername());
        foundUser.setPassword(userDetails.getPassword());

        return foundUser;
    }

}
