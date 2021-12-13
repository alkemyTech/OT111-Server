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
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;
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

    @Operation(summary = "Get all users.", security = @SecurityRequirement(name = "bearerAuth"),description = "Get full list of users in database, only accesible by an Administrator")
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean isRemoved = userService.deleteUser(id);
        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}



