package com.alkemy.ong.auth.controller;

import com.alkemy.ong.auth.dto.AuthenticationRequest;
import com.alkemy.ong.auth.dto.UserDTO;
import com.alkemy.ong.auth.service.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private UserDetailsCustomService userServ;

    @Autowired
    private AuthenticationManager authManager;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        UserDetails userDetails;

        try {
            UsernamePasswordAuthenticationToken newTry = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            );
            Authentication auth = authManager.authenticate(newTry);
            SecurityContextHolder.getContext().setAuthentication(auth);

            // Casteamos Dicho AUTH a UserDetails
            userDetails = (UserDetails) auth.getPrincipal();


        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        UserDTO foundUser = new UserDTO();
        foundUser.setUsername(userDetails.getUsername());
        foundUser.setPassword(userDetails.getPassword());
        return ResponseEntity.ok(foundUser);
    }
}
