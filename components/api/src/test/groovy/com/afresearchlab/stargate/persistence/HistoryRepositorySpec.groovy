package com.afresearchlab.stargate.persistence


import com.afresearchlab.stargate.spechelpers.IntegrationSpec
import org.springframework.beans.factory.annotation.Autowired

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HistoryRepositorySpec extends IntegrationSpec {

    @Autowired
    HistoryRepository subject

    def 'save() creates a new history entry'() {
        when:
        subject.save('username', 'action', 1)
        def result = subject.getAllByRfiId(1)

        then:
        result[0].username == 'username'
        result[0].action == 'action'
        LocalDateTime.parse(result[0].date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toLocalDate() == LocalDateTime.now().toLocalDate()
    }

    def 'getAllByRfiId() returns all entries for an Rfi id'() {
        given:
        subject.save('user1', 'action1', 1)
        Thread.sleep(1000) //So that ORDER BY date works
        subject.save('user1', 'action2', 1)
        subject.save('user2', 'action3', 2)

        when:
        def result = subject.getAllByRfiId(1)

        then:
        result.username == ['user1', 'user1']
        result.action == ['action2', 'action1']
        LocalDateTime.parse(result[0].date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toLocalDate() == LocalDateTime.now().toLocalDate()
        LocalDateTime.parse(result[1].date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toLocalDate() == LocalDateTime.now().toLocalDate()
    }

    def 'deleteAllByRfiId() removes all entries for an Rfi id'() {
        given:
        subject.save('user1', 'action1', 1)
        subject.save('user1', 'action2', 1)
        subject.save('user2', 'action3', 2)

        when:
        def resultBefore = subject.getAllByRfiId(1)
        subject.deleteAllByRfiId(1)
        def resultAfter = subject.getAllByRfiId(1)

        then:
        resultBefore.size() == 2
        resultAfter.size() == 0
    }
}
