package com.alkemy.ong.auth.controller;

<<<<<<< HEAD
import com.alkemy.ong.model.dto.UserDto;
import com.alkemy.ong.service.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
=======
import com.alkemy.ong.auth.service.UserAuthService;
import com.alkemy.ong.model.dto.AuthenticationRequest;
import com.alkemy.ong.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
>>>>>>> feature/segurity
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
import javax.validation.Valid;

=======
>>>>>>> feature/segurity
@RestController
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
<<<<<<< HEAD
    private UserDetailsCustomService userServ;

    // Signup
    @PostMapping("/register")
    public ResponseEntity<UserDto> signUp(@Valid @RequestBody UserDto userToCreate) throws Exception {

        UserDto userDto = userServ.signupUser(userToCreate);

        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

=======
    private AuthenticationManager authManager;

    @Autowired
    private UserAuthService userAuthServ;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        UserDTO userDetails = userAuthServ.loginAttempt(authenticationRequest);
        return ResponseEntity.ok(userDetails);
    }
>>>>>>> feature/segurity
}
