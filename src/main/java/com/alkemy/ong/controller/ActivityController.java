package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.ActivityRequestDTO;
import com.alkemy.ong.model.request.CategoryRequestDTO;
import com.alkemy.ong.model.response.CategoryResponseDTO;
import com.alkemy.ong.service.sendgrid.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivityController {

    @Autowired
    ActivityController activityController;

    @GetMapping("/activities")
    public String createActivity(@RequestBody ActivityRequestDTO request) {
        ActivityRequestDTO response = activityController.createActivity(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
