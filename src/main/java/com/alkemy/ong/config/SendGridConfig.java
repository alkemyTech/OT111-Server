package com.alkemy.ong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sendgrid.SendGrid;

@Configuration
public class SendGridConfig {

//    private String key = System.getenv("SENDGRID_API_KEY");
    private String key = "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";

    @Bean
    public SendGrid startSendGrid() {
        //inicializa sendgrid con su key
        return new SendGrid(key);
    }

}