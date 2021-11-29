package com.alkemy.ong.auth.controller;

import com.alkemy.ong.model.dto.UserDto;
import com.alkemy.ong.service.UserDetailsCustomService;
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
    private UserDetailsCustomService userServ;

    // Signup
    @PostMapping("/register")
    public ResponseEntity<UserDto> signUp(@Valid @RequestBody UserDto userToCreate) throws Exception {

        UserDto userDto = userServ.signupUser(userToCreate);

        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

}
