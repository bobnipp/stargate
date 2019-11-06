package com.afresearchlab.prismadapter;

import com.afresearchlab.prismadapter.service.RestService;
import com.afresearchlab.prismadapter.ssl.NaiveHostnameVerifier;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.Data;
import org.cometd.bayeux.Channel;
import org.cometd.bayeux.Message;
import org.cometd.bayeux.client.ClientSessionChannel;
import org.cometd.client.BayeuxClient;
import org.cometd.client.ext.AckExtension;
import org.cometd.client.ext.TimestampClientExtension;
import org.cometd.client.ext.TimesyncClientExtension;
import org.cometd.client.transport.ClientTransport;
import org.cometd.client.transport.LongPollingTransport;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpCookie;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.util.zip.GZIPInputStream;

import static com.afresearchlab.prismadapter.ssl.NaiveSSL.getTrustingSSLSocketFactory;

public class RealPrismCometClient implements PrismCometClient {
    private final RestTemplate restTemplate;
    private volatile BayeuxClient client;
    private String nomFilterId;
    private final String prismHost;
    private volatile boolean filteringComplete = false;

    public RealPrismCometClient(RestService restService) {
        this.restTemplate = restService.getRestTemplate();
        this.prismHost = restService.getHostName() + "/prism-webapp";
    }

    public String getNomList(String sessionId) {
        try {
            nomFilterId = getNomFilterRequestId(sessionId);

            SslContextFactory sslContextFactory = new SslContextFactory(true);
            HttpClient httpClient = new HttpClient(sslContextFactory);
            httpClient.start();

            ClientTransport transport = new LongPollingTransport(null, httpClient);

            client = new BayeuxClient(this.prismHost + "/cometd/", transport);
            client.putCookie(new HttpCookie(sessionId.split("=")[0], sessionId.split("=")[1]));
            client.addExtension(new AckExtension());
            client.addExtension(new TimesyncClientExtension());
            client.addExtension(new TimestampClientExtension());

            client.getChannel(Channel.META_HANDSHAKE).addListener(new TestHandshakeListener());
            client.getChannel(Channel.META_CONNECT).addListener(new TestConnectListener());
            ClientSessionChannel taskChannel = client.getChannel("/task/" + nomFilterId);

            client.handshake();
            boolean handshaken = client.waitFor(5000, BayeuxClient.State.CONNECTED);

            if (!handshaken) {
                System.out.println("Handshake unsuccessful :(");
                return sessionId;
            }

            System.out.println("Handshake successful!");
            while (!filteringComplete) {
            }
            taskChannel.unsubscribe();
            client.disconnect();
            return testGetNomListWithConnection(sessionId, client.getCookie("BAYEUX_BROWSER").getValue());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String testGetNomListWithConnection(String sessionId, String bayeuxBrowser) throws MalformedURLException {
        URL url = new URL(this.prismHost + "/rest/noms?_dc=" + Instant.now().toEpochMilli() + "&limit=500&start=0&filterType=NOM%2FAPPROVEVALIDATE&node=rootNode");

        System.out.println("FETCHING FINAL RESULTS");

        HttpsURLConnection connection = null;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            connection = (HttpsURLConnection) url.openConnection();
            connection.setHostnameVerifier(new NaiveHostnameVerifier());
            connection.setSSLSocketFactory(getTrustingSSLSocketFactory());

            connection.addRequestProperty("Host", url.getHost());
            connection.addRequestProperty("Cookie", sessionId + "; " + "BAYEUX_BROWSER=" + bayeuxBrowser);
            connection.addRequestProperty("Accept", "*/*");
            connection.addRequestProperty("Accept-Language", "en-US,en;q=0.9");
            connection.addRequestProperty("Accept-Encoding", "gzip, deflate, br");
            connection.addRequestProperty("Connection", "close");

            connection.setUseCaches(false);
            connection.setDoInput(true);

            InputStream inputStream = connection.getInputStream();

            if (connection.getHeaderField("Content-Encoding").equals("gzip")) {
                inputStream = new GZIPInputStream(inputStream);
            }

            int i;
            while ((i = inputStream.read()) != -1) {
                output.write(i);
            }

            connection.disconnect();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            if (connection != null) connection.disconnect();
        }

        return output.toString();
    }

    private String getNomFilterRequestId(String sessionId) {
        System.out.println("SESSION: " + sessionId);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", sessionId);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("filterType", "NOM/APPROVEVALIDATE");
        requestBody.add("prismState", "A");
        requestBody.add("organization", "View All");
        requestBody.add("sortBy", "NOM Name");
        requestBody.add("resultsPerPage", "25");
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(this.prismHost + "/rest/noms", entity, String.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Error POSTING to /rest/noms. Do your credentials still have access?");
        }
        System.out.println("NOM FILTER RESPONSE: " + response);

        return new Gson().fromJson(response.getBody(), CreateNomFilterResponse.class).getData();
    }

    private class TestHandshakeListener implements ClientSessionChannel.MessageListener {

        @Override
        public void onMessage(ClientSessionChannel channel, Message message) {
            System.out.println(String.format("MESSAGE RECEIVED FROM %s: '%s'", channel.getId(), message.getJSON()));
            if (message.isSuccessful()) {
                subscribeToFilter();
            }
        }
    }

    private void subscribeToFilter() {
        ClientSessionChannel taskChannel = client.getChannel("/task/" + nomFilterId);
        taskChannel.subscribe(new TestSubscribeListener());
    }

    private class TestSubscribeListener implements ClientSessionChannel.MessageListener {

        @Override
        public void onMessage(ClientSessionChannel channel, Message message) {
            System.out.println(String.format("Got message from %s, %s: ", channel.getId(), message.getJSON()));
            handleSubscriptionMessage(message);
        }
    }

    private void handleSubscriptionMessage(Message message) {
        JsonParser parser = new JsonParser();
        JsonElement jsonTree = parser.parse(message.getJSON());
        String subscriptionStatus = jsonTree.getAsJsonObject().get("data").getAsJsonObject().get("statusMessage").getAsString();
        if (subscriptionStatus.equals("DONE")) {
            filteringComplete = true;
        }
    }

    private class TestConnectListener implements ClientSessionChannel.MessageListener {

        @Override
        public void onMessage(ClientSessionChannel channel, Message message) {
            System.out.println("Got message from meta/connect: " + message.getJSON());
        }
    }


    @Data
    private class CreateNomFilterResponse {
        private final String data;
        private final String msg;
        private final boolean success;
    }
}

