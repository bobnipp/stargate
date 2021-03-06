package org.cloudfoundry.identity.uaa.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URLDecoder;

public class UaaLoginHint {
    private String origin;
    private static ObjectMapper mapper = new ObjectMapper();

    public static UaaLoginHint parseRequestParameter(String loginHint) {
        if (loginHint == null) {
            return null;
        }
        try {
            loginHint = URLDecoder.decode(loginHint, "UTF-8");
            return mapper.readValue(loginHint, UaaLoginHint.class);
        } catch (IOException e) {
            return null;
        }
    }

    private UaaLoginHint() {
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
