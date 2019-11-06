package com.afresearchlab.stargate.config;

import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.MetadataRestClient;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class JiraClientConfiguration {

    private final JiraRestClient restClient;

    public JiraClientConfiguration(@Value("${jira.url}") String url, @Value("${jira.username}") String username, @Value("${jira.password}") String password) throws URISyntaxException {
        this.restClient = new AsynchronousJiraRestClientFactory()
            .createWithBasicHttpAuthentication(new URI(url), username, password);
    }

    @Bean
    public SearchRestClient searchRestClient() {
        return this.restClient.getSearchClient();
    }

    @Bean
    public IssueRestClient issueRestClient() {
        return this.restClient.getIssueClient();
    }

    @Bean
    public MetadataRestClient metadataRestClient() {
        return this.restClient.getMetadataClient();
    }
}
