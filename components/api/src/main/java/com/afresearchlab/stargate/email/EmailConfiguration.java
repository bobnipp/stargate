package com.afresearchlab.stargate.email;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import static org.springframework.integration.dsl.channel.MessageChannels.queue;
import static org.springframework.integration.mail.dsl.Mail.imapInboundAdapter;

@Configuration
public class EmailConfiguration {

    private final String storeUri;
    private final Integer period;
    private Boolean emailTriggersEnabled;
    private EmailTransformer emailTransformer;

    public EmailConfiguration(
        @Value("${emailServer.protocol}://${emailServer.username}:${emailServer.password}@${emailServer.host}/INBOX") String storeUri,
        @Value("${emailServer.periodInSeconds}") Integer period,
        @Value("${featureFlags.emailTriggers}") Boolean emailTriggersEnabled,
        EmailTransformer emailTransformer
    ) {
        this.storeUri = storeUri;
        this.period = period;
        this.emailTriggersEnabled = emailTriggersEnabled;
        this.emailTransformer = emailTransformer;
    }

    @Bean
    public IntegrationFlow imapMailFlow() {
        if (emailTriggersEnabled) {
            return IntegrationFlows
                .from(imapInboundAdapter(this.storeUri).simpleContent(true),
                    e -> e.autoStartup(true)
                        .poller(p -> p.fixedDelay(this.period * 1000)))
                .transform(emailTransformer::transformMessage)
                .log()
                .channel(queue("imapChannel"))
                .get();
        }
        return null;
    }

    @Bean
    @Profile("!dev")
    public MailSender testMailSender(
        @Value("${spring.mail.host}") String hostName,
        @Value("${spring.mail.port}") Integer portNumber
    ) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(hostName);
        mailSender.setPort(portNumber);
        mailSender.setProtocol("smtp");
        return mailSender;
    }

    @Bean
    @Profile("dev")
    public MailSender sendgridMailSender(@Value("${sendgrid.apiKey}") String apiKey) {
        return new SendgridMailSender(new SendGrid(apiKey));
    }
}
