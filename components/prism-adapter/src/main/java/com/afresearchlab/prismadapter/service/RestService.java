package com.afresearchlab.prismadapter.service;

import com.afresearchlab.prismadapter.SSLClientHttpRequestFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

    private final Boolean useMock;
    private final String mockHost;
    private final String realHost;
    private final RestTemplate restTemplate;

    public RestService(@Value("${prism.useMock}") Boolean useMock,
                       @Value("${prism.mock}") String mockHost,
                       @Value("${prism.real}") String realHost) {
        this.useMock = useMock;
        this.mockHost = mockHost;
        this.realHost = realHost;
        if (useMock) {
            restTemplate = new RestTemplate();
        } else {
            restTemplate = new RestTemplate(new SSLClientHttpRequestFactory());
        }
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public String getHostName() {
        if (useMock) {
            return mockHost;
        }
        return realHost;
    }

}
