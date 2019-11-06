package com.afresearchlab.prismadapter.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Calendar;

@Service
public class SessionManager {

    private final Long timeout;
    Long sessionStartTime;

    public SessionManager(@Value("${prism.sessionTimeoutMilliseconds}") long sessionTimeoutMilliseconds) {
        timeout = sessionTimeoutMilliseconds;
        sessionStartTime = Calendar.getInstance().getTimeInMillis();
    }

    public boolean isSessionValid(String sessionId) {
        Long currentTime = Calendar.getInstance().getTimeInMillis();
        return !StringUtils.isEmpty(sessionId) && currentTime - sessionStartTime <= timeout;
    }

    public void updateSessionStart() {
        sessionStartTime = Calendar.getInstance().getTimeInMillis();
    }

}
