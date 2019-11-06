package com.afresearchlab.stargate.email;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class Mailer {

    private final MailSender mailSender;

    Mailer(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String emailAddress, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailAddress);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
