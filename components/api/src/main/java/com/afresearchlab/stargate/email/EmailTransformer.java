package com.afresearchlab.stargate.email;

import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class EmailTransformer {
    public EmailMessage transformMessage(MimeMessage msg) {
        try {
            return new EmailMessage(msg.getSubject(), getBody(msg));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private String getBody(MimeMessage msg) throws Exception {
        MimeMultipart content = (MimeMultipart) msg.getContent();
        InputStream in = content.getBodyPart(0).getInputStream();
        int n = in.available();
        byte[] bytes = new byte[n];
        in.read(bytes, 0, n);
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
