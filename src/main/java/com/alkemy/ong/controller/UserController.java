package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.user.UserUpdateDTO;
import com.alkemy.ong.model.response.user.UserResponseDTO;
import com.alkemy.ong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@Validated
@RequestMapping("users") //TODO check: en la tarea dice el path "a/users"??.
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateDTO userUpdateDTO, @PathVariable Long id){
        UserResponseDTO UserResponseDTO = userService.findById(id);
        if (UserResponseDTO == null){
            return ResponseEntity.notFound().build();
        }
        userService.updateUser(userUpdateDTO, id);
        return ResponseEntity.ok().build();
    }
}

//TODO Pull Request para agregar Path /users en SecurityConfiguration para que lo pueda solo trabajar un admin

