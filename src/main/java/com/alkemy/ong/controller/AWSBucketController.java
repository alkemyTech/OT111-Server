package com.alkemy.ong.controller;

import com.alkemy.ong.model.response.AWSResponseDTO;
import com.alkemy.ong.service.AWSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/storage")
public class AWSBucketController {

    @Autowired
    private AWSService awsService;

    @PostMapping("/upload")
    public ResponseEntity<AWSResponseDTO> uploadFile(@RequestPart(value = "file")MultipartFile file) {
        AWSResponseDTO response = awsService.uploadFile(file);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
