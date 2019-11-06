package com.afresearchlab.prismadapter.config;

import com.afresearchlab.prismadapter.FakePrismCometClient;
import com.afresearchlab.prismadapter.PrismCometClient;
import com.afresearchlab.prismadapter.RealPrismCometClient;
import com.afresearchlab.prismadapter.service.RestService;
import com.afresearchlab.prismadapter.ssl.NaiveHostnameVerifier;
import com.saic.prism.ws.coredataws.prismcoredataws.PRISMCoreDataWS;
import com.saic.prism.ws.coredataws.prismcoredataws.PRISMCoreDataWSEndPoint;
import com.saic.prism.ws.exnomws.prismexnomws.PRISMExNomWS;
import com.saic.prism.ws.exnomws.prismexnomws.PRISMExNomWSEndPoint;
import com.saic.prism.ws.researchws.prismresearchws.PRISMResearchWS;
import com.saic.prism.ws.researchws.prismresearchws.PRISMResearchWSEndPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import redis.clients.jedis.JedisPool;

import javax.xml.ws.BindingProvider;

import static com.afresearchlab.prismadapter.ssl.NaiveSSL.getTrustingSSLSocketFactory;


@Configuration
public class BeanConfiguration {
    @Value("${prism.useMock}")
    Boolean useMock;

    @Value("${prism.mock}")
    String mockHost;

    @Value("${prism.real}")
    String realHost;

    @Value("${redis.hostname}")
    String redisHost;

    @Value("${redis.port}")
    String redisPort;

    @Value("${redis.credentials.password}")
    String redisPassword;

    @Autowired
    RestService restService;

    private static final String JAXWS_HOSTNAME_VERIFIER = "com.sun.xml.internal.ws.transport.https.client.hostname.verifier";
    private static final String JAXWS_SSL_SOCKET_FACTORY = "com.sun.xml.internal.ws.transport.https.client.SSLSocketFactory";

    @Bean
    public PRISMResearchWSEndPoint prismResearchWSEndPoint() {
        String endpointName = "PRISMResearchWS";

        PRISMResearchWS ws = new PRISMResearchWS(getClass().getClassLoader().getResource(String.format("prism-wsdl/%s.wsdl", endpointName)));
        PRISMResearchWSEndPoint prismResearchWSEndPointPort = ws.getPRISMResearchWSEndPointPort();

        customizePort(prismResearchWSEndPointPort, endpointName);

        return prismResearchWSEndPointPort;
    }

    @Bean
    public PRISMCoreDataWSEndPoint prismCoreWSEndPoint() {
        String endpointName = "PRISMCoreDataWS";

        PRISMCoreDataWS ws = new PRISMCoreDataWS(getClass().getClassLoader().getResource(String.format("prism-wsdl/%s.wsdl", endpointName)));
        PRISMCoreDataWSEndPoint prismCoreDataWSEndPointPort = ws.getPRISMCoreDataWSEndPointPort();

        customizePort(prismCoreDataWSEndPointPort, endpointName);

        return prismCoreDataWSEndPointPort;
    }

    @Bean
    public PRISMExNomWSEndPoint prismExNomWSEndPoint() {
        String endpointName = "PRISMExNomWS";

        PRISMExNomWS ws = new PRISMExNomWS(getClass().getClassLoader().getResource(String.format("prism-wsdl/%s.wsdl", endpointName)));
        PRISMExNomWSEndPoint prismExNomWSEndPointPort = ws.getPRISMExNomWSEndPointPort();

        customizePort(prismExNomWSEndPointPort, endpointName);

        return prismExNomWSEndPointPort;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public PrismCometClient prismCometClient() {
        if (useMock) {
            return new FakePrismCometClient();
        } else {
            return new RealPrismCometClient(restService);
        }
    }

    @Bean
    @Profile("!aws")
    public JedisPool jedisPoolNonAws() {
        String uri = String.format("redis://:%s@%s:%s", redisPassword, redisHost, redisPort);
        return new JedisPool(uri);
    }

    @Bean
    @Profile("aws")
    public JedisPool jedisPoolForAws() {
        String uri = String.format("redis://%s:%s", redisHost, redisPort);
        return new JedisPool(uri);
    }

    private <T> void customizePort(T port, String endpoint) {
        String prismHost = useMock ? mockHost : realHost;
        ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, prismHost + "/" + endpoint);
        ((BindingProvider) port).getRequestContext().put(JAXWS_SSL_SOCKET_FACTORY, getTrustingSSLSocketFactory());
        ((BindingProvider) port).getRequestContext().put(JAXWS_HOSTNAME_VERIFIER, new NaiveHostnameVerifier());
    }
}
