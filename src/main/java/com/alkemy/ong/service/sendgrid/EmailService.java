package com.alkemy.ong.service.sendgrid;

public interface EmailService {

    public String sendEmail(String email);

    public String sendWithTemplate(String email, String user, String templateId);

    void sendContactConfirmation(String email);

}
