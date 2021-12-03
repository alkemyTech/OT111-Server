package com.alkemy.ong.controller;

import com.alkemy.ong.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api")
public class MailController {

    @Autowired
    MailService mailService;

    @PostMapping("/send-text")
    public String send()throws IOException{
        return mailService.SEND();
    }


}
