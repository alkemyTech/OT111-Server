package com.alkemy.ong.controller;

import com.alkemy.ong.service.sendgrid.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class EmailController {

    @Autowired
    EmailServiceImpl emailService;

    @GetMapping("/sendMail/{email}")
    public String sendEmail(@PathVariable(value = "email", required = true) String email)
    {
        return	emailService.sendEmail(email);
    }

    @GetMapping("/sendWhitTemplate/{email}")
    public String sendWithTemplate(@PathVariable(value = "email", required = true) String mail, @RequestParam String user, String templateId) throws IOException {

        return emailService.sendWithTemplate(mail, user, templateId);}
}