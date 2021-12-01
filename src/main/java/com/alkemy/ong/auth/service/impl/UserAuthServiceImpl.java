package com.alkemy.ong.auth.service.impl;

import com.alkemy.ong.auth.service.JwtUtil;
import com.alkemy.ong.auth.service.UserAuthService;
import com.alkemy.ong.model.dto.AuthenticationRequest;
import com.alkemy.ong.model.dto.UserDTO;
import com.alkemy.ong.model.entity.FoundUserEntity;
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
    private JwtUtil jwtTokenUtil;

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
            userDetails = (UserDetails) auth.getPrincipal();

        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        //TODO: Add All UserEntity attributes to UserDetails.
        UserDTO foundUser = new UserDTO();
        String jwt = jwtTokenUtil.generateToken(userDetails);
        System.out.println("UserDetails JWT --->  "+jwt);
        foundUser.setEmail(userDetails.getUsername());
        foundUser.setPassword(userDetails.getPassword());
        foundUser.setJwt(jwt);
        return foundUser;
    }
}
