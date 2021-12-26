package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.UserRequest;
import com.alkemy.ong.model.response.UserResponse;
import com.alkemy.ong.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Tag(name = "Usuarios")
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @GetMapping
    @Operation(summary = "Lista todos los usuarios.", security = @SecurityRequirement(name = "bearerAuth"),
            description = "Obtiene la lista completa de usuarios, solo accesible por un Administrador")
    public List<UserResponse> getAllUsers() {
        return userService.findAllUsers();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un usuario",
            description = "Elimina el usuario existente dado el ID pasado como parámetro por url, " +
                    "y si el usuario a eliminar no existe se lanza un error con código de estado 404. " +
                    "Solo accesible por un usuario regular")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Lista el detalle de un usuario")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public UserResponse getUserDetails(@PathVariable Long id){
        return userService.findUserById(id);

    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un usuario",
            description = "Actualiza el usuario existente dado el ID pasado como parámetro por url, " +
                    "y si el usuario a actualizar no existe se lanza un error con código de estado 404")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@RequestBody UserRequest request, @PathVariable Long id) {
        userService.updateUser(request, id);
    }
}


