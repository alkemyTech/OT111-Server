package com.alkemy.ong.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sendgrid.SendGrid;

@Configuration
public class SendGridConfig {

    @Value("${sendgrid.sendgrid.apiKey}")
    private String key;

    @Bean
    public SendGrid startSendGrid() {
        //inicializa sendgrid con su key
        return new SendGrid(key);
    }

}