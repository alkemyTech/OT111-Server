package com.alkemy.ong.auth.controller;

import com.alkemy.ong.auth.service.UserAuthService;
import com.alkemy.ong.auth.service.UserDetailsCustomService;
import com.alkemy.ong.exception.ApiExceptionResponse;
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
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
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
    @ApiResponses(
            {
                    @ApiResponse(

                            responseCode = "201",
                            description = "Usuario creado con exito.",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = RegisterResponse.class))
                            }
                    ),
                    @ApiResponse(

                            responseCode = "400",
                            description = "Email ya existente.",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = ApiExceptionResponse.class))

                            }
                    )
            }
    )
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> signUp(@Valid @RequestBody RegisterRequest userToCreate) {
        RegisterResponse userResponse = userDetailsCustomService.signupUser(userToCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @Operation(
            summary = "Login con Email y Password. Devolverá JWT",
            description = "Realizará un Login con Email y Password. Devolverá JWT."
    )
    @ApiResponses(
            {
                    @ApiResponse(

                            responseCode = "200",
                            description = "Usuario Loggeado con Exito.",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = AuthenticationResponse.class))
                            }
                    ),
                    @ApiResponse(

                            responseCode = "404",
                            description = "Username o Password Incorretos.",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = ApiExceptionResponse.class))

                            }
                    )
            }
    )
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        AuthenticationResponse userDetails = userAuthServ.loginAttempt(authenticationRequest);
        return ResponseEntity.ok(userDetails);
    }

    @Operation(
            summary = "Buscará la cuenta que iniciò sesion (Dentro del Context)",
            description = "Ver datos del usuario en Context."
    )
    @ApiResponses(
            {
                    @ApiResponse(

                            responseCode = "200",
                            description = "Datos del Usuario",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = UserDTO.class))
                            }
                    ),
                    @ApiResponse(

                            responseCode = "403",
                            description = "Prohibido el acceso, se necesita iniciar sesion(JWT)",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = AccessDeniedException.class))

                            }
                    )
            }
    )
    @GetMapping("/me")
    public ResponseEntity<UserDTO> meData(@CurrentSecurityContext(expression = "authentication")
                                                  Authentication authentication) {
        return ResponseEntity.ok(userAuthServ.meData(authentication.getName()));
    }

}
