package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.user.UserUpdateDTO;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@Validated
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Get all users.", security = @SecurityRequirement(name = "bearerAuth"), description = "Get full list of users in database, only accesible by an Administrator")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All users retrieved",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class))}),//UserDTO or UserEntity??
            @ApiResponse(
                    responseCode = "403",
                    description = "Access Denied, authorization needed",
                    content = @Content)})
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lista el detalle de un usuario")
    public ResponseEntity<UserDTO> getUserDetails(@PathVariable Long id){
        UserDTO userResponse = userService.findUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un usuario",
            description = "Actualiza el usuario existente dado el ID pasado como parámetro por url, " +
                    "y si el usuario a actualizar no existe se lanza un error con código de estado 404")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario actualizado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserUpdateDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    public ResponseEntity<Void> updateUser(@RequestBody UserUpdateDTO request, @PathVariable Long id) {
        userService.updateUser(request, id);
        return ResponseEntity.ok().build();
    }
}


