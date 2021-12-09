package com.alkemy.ong.controller;

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
    public ResponseEntity<String> uploadFile(@RequestPart(value = "file")MultipartFile file) {
        awsService.uploadFile(file);
        String response = "File " + file.getOriginalFilename() + " uploaded successfully.";
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }
}
