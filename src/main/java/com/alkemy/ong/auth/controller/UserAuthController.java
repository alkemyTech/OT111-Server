package com.alkemy.ong.auth.controller;

import com.alkemy.ong.auth.service.UserAuthService;
import com.alkemy.ong.auth.service.UserDetailsCustomService;
import com.alkemy.ong.model.request.security.AuthenticationRequest;
import com.alkemy.ong.model.request.security.RegisterRequest;
import com.alkemy.ong.model.response.security.AuthenticationResponse;
import com.alkemy.ong.model.response.security.RegisterResponse;
import com.alkemy.ong.model.response.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/me")
    public ResponseEntity<UserDTO> meData(@CurrentSecurityContext(expression = "authentication")
                                        Authentication authentication) {
        return ResponseEntity.ok(userAuthServ.meData(authentication.getName()));

    }

}
