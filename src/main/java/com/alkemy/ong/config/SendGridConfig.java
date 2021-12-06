package com.alkemy.ong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sendgrid.SendGrid;

@Configuration
public class SendGridConfig {

    private String key = System.getenv("SENDGRID_API_KEY");

    @Bean
    public SendGrid startSendGrid() {
        //inicializa sendgrid con su key
        return new SendGrid(key);
    }
}