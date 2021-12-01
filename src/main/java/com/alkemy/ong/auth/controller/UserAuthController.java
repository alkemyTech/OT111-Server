package com.alkemy.ong.auth.controller;

import com.alkemy.ong.auth.service.UserAuthService;
import com.alkemy.ong.model.dto.AuthenticationRequest;
import com.alkemy.ong.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserAuthService userAuthServ;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        UserDTO userDetails = userAuthServ.loginAttempt(authenticationRequest);
        return ResponseEntity.ok(userDetails);
    }

}
