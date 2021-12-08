package com.alkemy.ong.service.impl;

import com.alkemy.ong.service.AWSService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class AWSServiceImpl implements AWSService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AWSServiceImpl.class);

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.bucketName}")
    private String bucketName;

    @Override
    public void uploadFile(MultipartFile file) {
        File mainFile = new File(file.getOriginalFilename());
        try(FileOutputStream stream = new FileOutputStream(mainFile)) {
            stream.write(file.getBytes());
            String newFileName = System.currentTimeMillis() + "_" + mainFile.getName();
            LOGGER.info("Uploading file..." + newFileName);
            PutObjectRequest request = new PutObjectRequest(bucketName, newFileName, mainFile);
            amazonS3.putObject(request);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(),e);
        }
    }
}
