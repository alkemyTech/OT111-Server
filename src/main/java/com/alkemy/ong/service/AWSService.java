package com.alkemy.ong.service;

import com.alkemy.ong.model.response.AWSResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public interface AWSService {

    AWSResponseDTO uploadFile(MultipartFile file);
}
