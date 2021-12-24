package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.UserRequest;
import com.alkemy.ong.model.response.UserResponse;
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
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Lista todos los usuarios.", security = @SecurityRequirement(name = "bearerAuth"), description = "Obtiene la lista completa de usuarios, solo accesible por un Administrador")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))}),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = @Content)})
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un usuario",
            description = "Elimina el usuario existente dado el ID pasado como parámetro por url, " +
                    "y si el usuario a eliminar no existe se lanza un error con código de estado 404. " +
                    "Solo accesible por un usuario regular")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario eliminado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @GetMapping("/{id}")
    @Operation(summary = "Lista el detalle de un usuario")
    public ResponseEntity<UserResponse> getUserDetails(@PathVariable Long id){
        UserResponse userResponse = userService.findUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un usuario",
            description = "Actualiza el usuario existente dado el ID pasado como parámetro por url, " +
                    "y si el usuario a actualizar no existe se lanza un error con código de estado 404")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario actualizado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserRequest.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    public ResponseEntity<Void> updateUser(@RequestBody UserRequest request, @PathVariable Long id) {
        userService.updateUser(request, id);
        return ResponseEntity.ok().build();
    }
}


