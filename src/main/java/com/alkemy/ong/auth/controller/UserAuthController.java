package com.alkemy.ong.auth.controller;

import com.alkemy.ong.auth.service.UserAuthService;
import com.alkemy.ong.auth.service.UserDetailsCustomService;
import com.alkemy.ong.model.request.security.AuthenticationRequest;
import com.alkemy.ong.model.request.security.RegisterRequest;
import com.alkemy.ong.model.response.security.AuthenticationResponse;
import com.alkemy.ong.model.response.security.RegisterResponse;
import com.alkemy.ong.model.response.user.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @Operation(summary = "Register (and login) a new user.",description = "Register a new user in system, login, and send mail confirmation")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "201",
                    description = "User registered successfully",
                    content = {@Content(mediaType = "application/json"
                            )}))
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> signUp(@Valid @RequestBody RegisterRequest userToCreate) {
        RegisterResponse userResponse = userDetailsCustomService.signupUser(userToCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @Operation(summary = "User Login with email and password.",description = "Login a previously registered user with email and password in system")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "User logged in successfully",
                    content = {@Content(mediaType = "application/json"
                    )}))
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        AuthenticationResponse userDetails = userAuthServ.loginAttempt(authenticationRequest);
        return ResponseEntity.ok(userDetails);
    }

}
