package com.alkemy.ong.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {

    @Value("${amazonProperties.aws.accessKey}")
    private String accessKeyId;
    @Value("${amazonProperties.aws.secretKey}")
    private String secretKey;
    @Value("${amazonProperties.aws.region}")
    private String region;

    @Bean
    public AmazonS3 getS3Client() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKeyId, secretKey);
        return AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region)).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
    }
}
