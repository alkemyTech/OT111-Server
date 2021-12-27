package com.alkemy.ong.auth.controller;

import com.alkemy.ong.auth.service.UserAuthService;
import com.alkemy.ong.auth.service.UserDetailsCustomService;
import com.alkemy.ong.model.request.security.AuthenticationRequest;
import com.alkemy.ong.model.request.security.RegisterRequest;
import com.alkemy.ong.model.response.security.AuthenticationResponse;
import com.alkemy.ong.model.response.security.RegisterResponse;
import com.alkemy.ong.model.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Tag(name = "Autenticación")
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private UserDetailsCustomService userDetailsCustomService;

    @Autowired
    private UserAuthService userAuthServ;

    // Signup
    @Operation(summary = "Registrar usuario y devolver JWT", description = "Registrará nuevo usuario con email y contraseña(será codificada con Bcrypt). Devolverá JWT para user usada.")
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse signUp(@Valid @RequestBody RegisterRequest userToCreate) {
        return userDetailsCustomService.signupUser(userToCreate);
    }

    @Operation(
            summary = "Login con Email y Password. Devolverá JWT",
            description = "Realizará un Login con Email y Password. Devolverá JWT."
    )
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        return userAuthServ.loginAttempt(authenticationRequest);
    }

    @Operation(
            summary = "Buscará la cuenta que iniciò sesion (Dentro del Context)",
            description = "Ver datos del usuario en Context."
    )

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse meData(@CurrentSecurityContext(expression = "authentication") Authentication authentication) {
        return userAuthServ.meData(authentication.getName());
    }

}
