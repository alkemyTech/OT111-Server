package com.alkemy.ong.service.sendgrid;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sendgrid.helpers.mail.objects.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

@Service
public class EmailServiceImpl implements EmailService {

    Logger logger = Logger.getLogger(EmailServiceImpl.class.getName());

    @Value("${sendgrid.sendgrid.sender}")
    private String sender;

    @Value("${sendgrid.welcome-template}")
    private String templateId;

    @Autowired
    SendGrid sendGrid;

    @Override
    public String sendEmail(String email) {


        try {
            Mail mail = customMail(email);
            Request request = new Request();

            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sendGrid.api(request);


            if (response != null) {
                logger.log(Level.INFO, "codigo respuesta desde sendgrid {0} ", response.getStatusCode());
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "error al enviar mail";
        }
        return "El mail se envió correctamente.";

    }

    @Override
    public String sendWithTemplate(String email, String user, String templateId) {
        Mail mail1 = new Mail();
        Email fromEmail = new Email();
        fromEmail.setEmail(sender);
        mail1.setFrom(fromEmail);
        Email to = new Email();
        to.setEmail(email);


        Personalization personalization1 = new Personalization();
        personalization1.addTo(to);

        personalization1.addDynamicTemplateData("nombre", user);
        mail1.addPersonalization(personalization1);
        mail1.setTemplateId(templateId);


        try {

            Request request = new Request();

            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail1.build());

            Response response = sendGrid.api(request);


            if (response != null) {
                logger.log(Level.INFO, "codigo respuesta desde sendgrid {0} ", response.getStatusCode());

            }

        } catch (IOException e) {
            e.printStackTrace();
            return "error al enviar mail";
        }
        return "El mail se envió correctamente.";

    }

    @Override
    public void sendContactConfirmation(String email) {
        // Email Data:
        Email fromEmail = new Email(sender);
        Email toEmail = new Email(email);
        Content content = new Content(
                "text/plain",
                "Gracias por su contacto, recibirá una respuesta brevemente."
        );
        String subject = "Fundacion Somos Mas";
        // Create:
        Mail mail = new Mail(fromEmail, subject, toEmail, content);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            if (response != null) {
                logger.log(Level.INFO, "codigo respuesta desde sendgrid {0} ", response.getStatusCode());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Mail customMail(String email) {

        Mail mail = new Mail();
        Email fromEmail = new Email();

        fromEmail.setEmail(sender);
        mail.setFrom(fromEmail);

        Email to = new Email();
        to.setEmail(email);

        //template de sendgrid
        Personalization personalization = new Personalization();
        personalization.addTo(to);
        mail.addPersonalization(personalization);
        mail.setTemplateId(templateId);

        return mail;

    }


}