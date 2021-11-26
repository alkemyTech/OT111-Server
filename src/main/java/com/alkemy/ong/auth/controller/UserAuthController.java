package com.alkemy.ong.auth.controller;

import com.alkemy.ong.auth.dto.AuthenticationRequest;
import com.alkemy.ong.auth.dto.AuthenticationResponse;
import com.alkemy.ong.auth.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        // Service should do:
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        UserDetails myUser = (UserDetails) auth.getPrincipal();
        UserDTO loggedUser = new UserDTO();
        loggedUser.setUsername(myUser.getUsername());

        AuthenticationResponse myResponse = new AuthenticationResponse();
        myResponse.setAuthenticatedUser(loggedUser);

        return ResponseEntity.ok(myResponse);
    }
}
