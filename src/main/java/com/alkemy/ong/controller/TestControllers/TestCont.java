package com.alkemy.ong.controller.TestControllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestCont {

   @GetMapping("/auth")
    public ResponseEntity<String> test(){
      return ResponseEntity.ok("Test AUTHENTICATED Controller OK");
   }

    @GetMapping("/user")
    public ResponseEntity<String> testUser(){
        return ResponseEntity.ok("Test USER Controller OK");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> testAdmin(){
        return ResponseEntity.ok("Test ADMIN Controller OK");
    }

    @GetMapping("/user-admin")
    public ResponseEntity<String> testUserAdmin(){
        return ResponseEntity.ok("Test ADMIN & USER Controller OK");
    }

}
