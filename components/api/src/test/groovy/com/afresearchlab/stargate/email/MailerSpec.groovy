package com.afresearchlab.stargate.email

import org.springframework.mail.MailSender
import org.springframework.mail.SimpleMailMessage
import spock.lang.Specification

class MailerSpec extends Specification {
    def 'mailer sends a message with the correct format'() {
        given:
        def mailSender = Mock(MailSender)
        def subject = new Mailer(mailSender)

        when:
        subject.sendMail('email', 'subject', 'body')

        then:
        1 * mailSender.send(_ as SimpleMailMessage) >> { SimpleMailMessage message ->
            assert message.to[0] == 'email'
            assert message.subject == 'subject'
            assert message.text == 'body'
        }
    }
}
