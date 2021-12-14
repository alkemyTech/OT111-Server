package com.alkemy.ong.service;

import org.springframework.web.multipart.MultipartFile;

public interface AWSService {

    void uploadFile(MultipartFile file);
}
