package com.afresearchlab.prismadapter.service;

import com.afresearchlab.prismadapter.PrismCometClient;
import com.afresearchlab.prismadapter.ServiceUnavailableException;
import com.afresearchlab.prismadapter.model.PrismUiNomination;
import com.afresearchlab.prismadapter.transformer.PrismUiHistoryTransformer;
import com.afresearchlab.prismadapter.transformer.PrismUiNominationTransformer;
import com.afresearchlab.prismadaptermodels.RecordHistory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.inject.Provider;
import java.util.List;

@Service
public class PrismUiService {
    private final Provider<PrismCometClient> cometClientProvider;
    private final RestTemplate restTemplate;
    private final String prismHost;
    private final String prismUsername;
    private final String prismPassword;
    private final SessionManager sessionManager;
    private String sessionId; //TODO: can we cache this elsewhere?

    public PrismUiService(Provider<PrismCometClient> cometClientProvider,
                          RestService restService,
                          @Value("${prism.ui.username}") String prismUsername,
                          @Value("${prism.ui.password}") String prismPassword,
                          SessionManager sessionManager) {
        this.cometClientProvider = cometClientProvider;
        this.restTemplate = restService.getRestTemplate();
        this.prismHost = restService.getHostName();
        this.prismUsername = prismUsername;
        this.prismPassword = prismPassword;
        this.sessionManager = sessionManager;
    }

    public void prismHealth() throws ServiceUnavailableException {
        try {
            restTemplate.getForObject(prismHost + "/prism-webapp/", String.class);
        } catch (RestClientException e) {
            throw new ServiceUnavailableException(e.getMessage());
        }
    }

    public String getSessionId() {
        if (!sessionManager.isSessionValid(sessionId)) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("j_username", this.prismUsername);
            map.add("j_password", this.prismPassword);
            map.add("j_database", "PR7AWS");

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(prismHost + "/prism-webapp/j_spring_security_check", request, String.class);
            sessionId = response.getHeaders().getFirst("Set-Cookie").split(";")[0];
            sessionManager.updateSessionStart();
        }

        return sessionId;
    }

    public List<PrismUiNomination> getAllNominations() {
        PrismCometClient prismCometClient = cometClientProvider.get();
        String nomList = prismCometClient.getNomList(getSessionId());
        return PrismUiNominationTransformer.transform(nomList);
    }

    public List<RecordHistory> getNominationHistory(String key) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", getSessionId());

        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                prismHost + "/prism-webapp/rest/system/history/NOM/" + key,
                HttpMethod.GET, requestEntity, String.class);

        return PrismUiHistoryTransformer.transform(response.getBody());
    }
}
