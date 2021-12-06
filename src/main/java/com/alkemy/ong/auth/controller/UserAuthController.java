package com.alkemy.ong.auth.controller;

import com.alkemy.ong.auth.service.UserAuthService;
import com.alkemy.ong.auth.service.UserDetailsCustomService;
import com.alkemy.ong.model.request.security.AuthenticationRequest;
import com.alkemy.ong.model.request.security.RegisterRequest;
import com.alkemy.ong.model.response.security.AuthenticationResponse;
import com.alkemy.ong.model.response.security.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private UserDetailsCustomService userDetailsCustomService;

    @Autowired
    private UserAuthService userAuthServ;

    // Signup
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> signUp(@Valid @RequestBody RegisterRequest userToCreate) {
        RegisterResponse userResponse = userDetailsCustomService.signupUser(userToCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        AuthenticationResponse userDetails = userAuthServ.loginAttempt(authenticationRequest);
        return ResponseEntity.ok(userDetails);
    }

}
