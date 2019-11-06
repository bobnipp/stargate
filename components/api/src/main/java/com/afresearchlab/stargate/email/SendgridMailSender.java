package com.afresearchlab.stargate.email;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class SendgridMailSender implements MailSender {

    private final SendGrid sendGrid;

    public SendgridMailSender(SendGrid sendGrid) {
        this.sendGrid = sendGrid;
    }

    @Override
    public void send(SimpleMailMessage simpleMessage) {
        Email from = new Email("test@example.com");
        String subject = simpleMessage.getSubject();
        List<Email> to = stream(simpleMessage.getTo()).map(Email::new).collect(Collectors.toList());
        Content content = new Content("text/plain", simpleMessage.getText());

        to.forEach(it -> {
            Mail mail = new Mail(from, subject, it, content);
            Request request = new Request();
            try {
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                sendGrid.api(request);
            } catch (IOException ex) {
                throw new RuntimeException("There was an error sending the notification.", ex);
            }
        });
    }

    @Override
    public void send(SimpleMailMessage... simpleMessages) {
        stream(simpleMessages).forEach(this::send);
    }
}
