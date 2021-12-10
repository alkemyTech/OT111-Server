package com.alkemy.ong.controller;

import com.alkemy.ong.model.entity.UserEntity;
import com.alkemy.ong.model.response.user.UserDTO;
import com.alkemy.ong.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@Validated
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Obtener todos los usuarios.", security = @SecurityRequirement(name = "bearerAuth"),description = "Obtener lista completa de usuarios, solo accesible por administrador")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Se obtuvieron todos los usuarios en el sistema",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(
                    responseCode = "403",
                    description = "Acceso denegado",
                    content = @Content)})
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }
}



