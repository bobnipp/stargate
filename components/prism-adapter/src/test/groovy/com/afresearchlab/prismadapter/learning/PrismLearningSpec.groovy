package com.afresearchlab.prismadapter.learning

import com.afresearchlab.prismadapter.RealPrismCometClient
import com.afresearchlab.prismadapter.SSLClientHttpRequestFactory
import com.afresearchlab.prismadapter.service.RestService
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.config.Registry
import org.apache.http.config.RegistryBuilder
import org.apache.http.conn.socket.ConnectionSocketFactory
import org.apache.http.conn.socket.PlainConnectionSocketFactory
import org.apache.http.conn.ssl.NoopHostnameVerifier
import org.apache.http.conn.ssl.SSLConnectionSocketFactory
import org.apache.http.conn.ssl.TrustSelfSignedStrategy
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager
import org.apache.http.ssl.SSLContextBuilder
import org.springframework.http.*
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import spock.lang.Ignore
import spock.lang.Specification

@Ignore('Hitting real PRISM')
class PrismLearningSpec extends Specification {

    /*
    Had to download the certificate with firefox and import into key store:
    sudo keytool -importcert -alias dev -file romeap01lintgreadisdevnorthgrumcom.crt -keystore /Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home/jre/lib/security/cacerts
     */

//    def host = 'https://ec2-96-127-47-237.us-gov-west-1.compute.amazonaws.com/prism-webapp'
    def host = 'https://ec2-96-127-47-237.us-gov-west-1.compute.amazonaws.com/prism-webapp'

    //One fix for this error: hostname does not match the certificate subject provided by the peer (using RestTemplate)
    def 'send GET request to PRISM'() {
        given:
        def restTemplate = new RestTemplate(new SSLClientHttpRequestFactory())
        def targetUrl = host + '/rest/requirement/imint/cr/000000000001502PR7AWS?_dc=1533152215898'
        String json = restTemplate.getForObject(targetUrl, String.class)

        expect:
        println "crap"
    }

    //One fix for this error: hostname does not match the certificate subject provided by the peer (using HttpClient)
    def 'send another GET request to PRISM'() {
        given:
        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(builder.build(), NoopHostnameVerifier.INSTANCE);
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create()
            .register("http", new PlainConnectionSocketFactory())
            .register("https", sslConnectionSocketFactory)
            .build();

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        cm.setMaxTotal(100);
        CloseableHttpClient httpclient = HttpClients.custom()
            .setSSLSocketFactory(sslConnectionSocketFactory)
            .setConnectionManager(cm)
            .build();
        def targetUrl = host + '/rest/requirement/imint/cr/000000000001502PR7AWS?_dc=1533152215898'

        HttpGet httpPost = new HttpGet(targetUrl);
        CloseableHttpResponse response = httpclient.execute(httpPost);


        expect:
        print response
    }

    def 'print cookie needed for login'() {
        given:
        def url = host + '/j_spring_security_check'
        def restTemplate = new RestTemplate(new SSLClientHttpRequestFactory())

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add('j_username', 'JGUSTINE');
        map.add('j_password', '1qaz@WSX3edc$RFV');
        map.add('j_database', 'PR7AWS');

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        def cookie = response.headers.getFirst('Set-Cookie').split(';')[0]
        expect:


        print cookie
    }

    def 'login and set cookie and retrieve collection requirement'() {
        given:
        def cookie = loginAndGetCookie()

        def targetUrl = host + '/rest/requirement/imint/cr/000000000001502PR7AWS?_dc=1533152215898'

        HttpHeaders headers = new HttpHeaders()
        headers.set('Cookie', cookie)

        HttpEntity entity = new HttpEntity(headers)

        def restTemplate = new RestTemplate(new SSLClientHttpRequestFactory())
        ResponseEntity<String> r = restTemplate.exchange(targetUrl, HttpMethod.GET, entity, String.class)

        expect:
        print r
    }

    def loginAndGetCookie() {
        def url = host + '/j_spring_security_check'
        def restTemplate = new RestTemplate(new SSLClientHttpRequestFactory())

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add('j_username', 'STHOMAS');
        map.add('j_password', 'Educate80authority!');
        map.add('j_database', 'PR7AWS');

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        return response.headers.getFirst('Set-Cookie').split(';')[0]
    }

    def 'create a nomination'() {
        given:
        def cookie = loginAndGetCookie()
        def url = host + '/rest/nom/basicView'
        def restTemplate = new RestTemplate(new SSLClientHttpRequestFactory())

        expect:
        def formData = [
            'nomId'                  : '',
            'nomName'                : 'LEARNINGNOM',
            'precedence'             : 'R',
            'justification'          : '',
            'ext-comp-1098-checkbox' : 'on',
            'imintChunkKey'          : '',
            'imintOrganization'      : 'ACC TEST',
            'imintReworkOrganization': '',
            'comments'               : '',
            'existingComments'       : '',
            'associatedCOI'          : ''
        ]

        def map = new LinkedMultiValueMap<String, String>();
        map.add('type', 'NOM')
        map.add('deleted', 'off')
        map.add('delinked', 'off')
        map.add('editable', 'on')
        map.add('linked', 'off')
        map.add('formData', new JsonBuilder(formData).toString())

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED)
        headers.set('Cookie', cookie)
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers)
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class)

        def slurper = new JsonSlurper()

        def createNomSubscriptionId = slurper.parseText(response.body).data

        println "Create Nomination Subscription Id thing = ${createNomSubscriptionId}"
        sleep(1000)
        def newUrl = "${host}/rest/nom/basicView/get-result/${createNomSubscriptionId}"

        HttpEntity entity = new HttpEntity(headers)
        ResponseEntity<String> response2 = restTemplate.exchange(newUrl.toString(), HttpMethod.GET, entity, String.class)

        println "NominationId = ${slurper.parseText(response2.body).data.recordId}"
    }

    def 'create a nomination and retrieve it'() {
        def cookie = loginAndGetCookie()
        def url = host + '/rest/nom/basicView'
        def restTemplate = new RestTemplate(new SSLClientHttpRequestFactory())

        expect:
        def formData = [
            'nomId'                  : '',
            'nomName'                : 'LEARNINGNOM',
            'precedence'             : 'R',
            'justification'          : '',
            'ext-comp-1098-checkbox' : 'on',
            'imintChunkKey'          : '',
            'imintOrganization'      : 'ACC TEST',
            'imintReworkOrganization': '',
            'comments'               : '',
            'existingComments'       : '',
            'associatedCOI'          : ''
        ]

        def map = new LinkedMultiValueMap<String, String>();
        map.add('type', 'NOM')
        map.add('deleted', 'off')
        map.add('delinked', 'off')
        map.add('editable', 'on')
        map.add('linked', 'off')
        map.add('formData', new JsonBuilder(formData).toString())

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED)
        headers.set('Cookie', cookie)
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers)
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class)

        def slurper = new JsonSlurper()

        def createNomSubscriptionId = slurper.parseText(response.body).data

        sleep(1000)
        def geturl = "${host}/rest/nom/basicView/get-result/${createNomSubscriptionId}"

        HttpEntity entity = new HttpEntity(headers)
        ResponseEntity<String> response2 = restTemplate.exchange(geturl.toString(), HttpMethod.GET, entity, String.class)

        def nominationId = slurper.parseText(response2.body).data.recordId

        def getNomRequest = "${host}/rest/nom/basicView/${nominationId}"
        ResponseEntity<String> response3 = restTemplate.exchange(getNomRequest.toString(), HttpMethod.GET, entity, String.class)

        def nominationRequestId = slurper.parseText(response3.body).data

        // async request so need to sleep a bit
        sleep(1000)

        def getNom = "${host}/rest/nom/basicView/get-result/${nominationRequestId}"

        ResponseEntity<String> response4 = restTemplate.exchange(getNom.toString(), HttpMethod.GET, entity, String.class)

        println url + ": " + request
        println response

        println geturl
        println slurper.parseText(response2.body)

        println getNomRequest
        println slurper.parseText(response3.body)

        println getNom
        println slurper.parseText(response4.body)
    }

    def 'retrieve nominations'() {
        def cookie = loginAndGetCookie()
        def restTemplate = new RestTemplate(new SSLClientHttpRequestFactory())

        expect:
        def slurper = new JsonSlurper()
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED)
        headers.set('Cookie', cookie)
        HttpEntity entity = new HttpEntity(headers)

        // START FILTER
        def filterMap = new LinkedMultiValueMap<String, String>()
        filterMap.add('filterType', 'NOM/APPROVEVALIDATE')
        filterMap.add('todayNOMs', 'on')
        filterMap.add('prismState', 'A')
        filterMap.add('organization', 'View All')
        filterMap.add('sortBy', 'NOM Name')
        filterMap.add('resultsPerPage', '200')

        HttpEntity<MultiValueMap<String, String>> filterEntity = new HttpEntity<MultiValueMap<String, String>>(filterMap, headers)

        def filterRequestUrl = host + '/rest/noms'

        ResponseEntity<String> responseFilterRequest = restTemplate.postForEntity(filterRequestUrl, filterEntity, String.class)
        def nomFilterId = slurper.parseText(responseFilterRequest.body).data
        // END FILTER

        sleep(5000)

        // START META-DATA RETRIEVE
        def filterIdUrl = "${host}/rest/noms/${nomFilterId}"
        ResponseEntity<String> responseFilterId = restTemplate.exchange(filterIdUrl.toString(), HttpMethod.GET, entity, String.class)
        // END META-DATA RETRIEVE

        sleep(5000)

        // START DATA RETRIEVE
        def getFilterInfoUrl = host + '/rest/noms?limit=25&start=0&filterType=NOM/APPROVEVALIDATE&node=rootNode'
        ResponseEntity<String> responseFilterInfo = restTemplate.exchange(getFilterInfoUrl, HttpMethod.GET, entity, String.class)
        // END DATA RETRIEVE

        println responseFilterRequest
        println responseFilterId
        println responseFilterInfo
    }

    def 'get nom list with comet'() {
        given:
        def cookie = loginAndGetCookie()
        def prismCometClient = new RealPrismCometClient(new RestService(false, '', host))
        def result = prismCometClient.getNomList(cookie)

        expect:
        println "comet done"
        println "NOM List Result:"
        println result
    }
}
