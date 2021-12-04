package com.alkemy.ong.controller;

import com.alkemy.ong.model.response.user.UserDTO;
import com.alkemy.ong.service.UserService;
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
@RequestMapping("users") //TODO check: en la tarea dice el path "a/users"??.
public class UserController {

    @Autowired
    private UserService userService;



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

//TODO Pull Request para agregar Path /users en SecurityConfiguration para que lo pueda solo trabajar un admin

