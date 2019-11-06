package com.afresearchlab.prismadapter.service

import spock.lang.Specification

class SessionManagerTest extends Specification {
    def sessionCheck = new SessionManager(500)

    def 'session is invalid due to no session id'() {
        given:
        def sessionId = null

        when:
        def isValid = sessionCheck.isSessionValid(sessionId)

        then:
        !isValid
    }

    def 'session is invalid due to expiration'() {
        given:
        def sessionId = "someSessionId"

        when:
        def isValid = sessionCheck.isSessionValid(sessionId)
        Thread.sleep(600)
        def stillIsValid = sessionCheck.isSessionValid(sessionId)

        then:
        isValid
        !stillIsValid
    }

    def 'updateSessionStart() updates the session start time'() {
        given:
        def oldStartTime = sessionCheck.sessionStartTime

        when:
        Thread.sleep(600)
        sessionCheck.updateSessionStart()

        then:
        sessionCheck.sessionStartTime > oldStartTime
    }

}
