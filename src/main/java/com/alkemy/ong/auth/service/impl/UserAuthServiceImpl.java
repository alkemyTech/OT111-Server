package com.alkemy.ong.auth.service.impl;

import com.alkemy.ong.auth.service.JwtUtil;
import com.alkemy.ong.auth.service.UserAuthService;
import com.alkemy.ong.model.mapper.AuthenticationMapper;
import com.alkemy.ong.model.request.security.AuthenticationRequest;
import com.alkemy.ong.model.response.security.AuthenticationResponse;
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

    @Autowired
    private AuthenticationMapper authenticationMapper;

    @Override
    public AuthenticationResponse loginAttempt(AuthenticationRequest authenticationRequest) throws Exception {
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

        // Build Response:
        String jwt = jwtTokenUtil.generateToken(userDetails);
        return authenticationMapper.userDetailsAndJwt2LoginResponseDTO(userDetails, jwt);
    }

}
