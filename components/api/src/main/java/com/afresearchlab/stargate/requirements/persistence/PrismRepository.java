package com.afresearchlab.stargate.requirements.persistence;

import com.afresearchlab.prismadaptermodels.FullNom;
import com.afresearchlab.stargate.healthcheck.HealthCheck;
import com.saic.prism.ws.coredataws.prismcoredataws.PrismNom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import java.util.List;

@Service
public class PrismRepository {
    private final RestTemplate restTemplate;
    private final String prismHost;

    public PrismRepository(RestTemplate restTemplate, @Value("${prism-adapter.host-name}") String prismHost) {
        this.restTemplate = restTemplate;
        this.prismHost = prismHost;
    }

    public FullNom getNominationById(String id) {
        try {
            ResponseEntity<FullNom> response = this.restTemplate.exchange(
                    UriComponentsBuilder.fromHttpUrl(this.prismHost).pathSegment("nominations", UriUtils.encodePathSegment(id, "UTF-8")).build(true).toUri(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<FullNom>() {
                    });
            return response.getBody();
        } catch (HttpServerErrorException e) {
            return null;  // could not find nomination
        }
    }

    public List<PrismNom> getAllNominations() {
        ResponseEntity<List<PrismNom>> response = this.restTemplate.exchange(
                this.prismHost + "/nominations",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PrismNom>>() {
                });
        return response.getBody();
    }

    public HealthCheck healthCheck() {
        return this.restTemplate.getForObject(this.prismHost + "/healthcheck", HealthCheck.class);
    }
}
