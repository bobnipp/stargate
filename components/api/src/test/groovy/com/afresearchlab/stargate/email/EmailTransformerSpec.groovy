package com.afresearchlab.stargate.email

import spock.lang.Specification

import javax.mail.BodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart

class EmailTransformerSpec extends Specification {

    def 'it grabs the subject and body from incoming messages'() {
        given:
        def subject = new EmailTransformer()
        def mockMessage = Mock(MimeMessage) {
            getContent() >> Mock(MimeMultipart) {
                getBodyPart(0) >> Mock(BodyPart) {
                    getInputStream() >> new ByteArrayInputStream('Body content'.getBytes())
                }
            }
            getSubject() >> 'Subject line'
        }

        when:
        def emailMessage = subject.transformMessage(mockMessage)

        then:
        emailMessage.getSubject() == 'Subject line'
        emailMessage.getBody() == 'Body content'
    }
}
