package com.afresearchlab.prismadapter

import com.afresearchlab.prismadapter.service.RestService
import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import spock.lang.Unroll

class RestServiceSpec extends Specification {

    @Unroll
    def 'get appropriate rest template for configuration'() {
        given:
        RestService restService = new RestService(useMock, '', '')

        expect:
        RestTemplate restTemplate = restService.getRestTemplate()
        restTemplate.getRequestFactory().getClass().getSimpleName() == expectedClassName

        where:
        useMock | expectedClassName
        true    | 'SimpleClientHttpRequestFactory'
        false   | 'SSLClientHttpRequestFactory'
    }

    @Unroll
    def 'get appropriate prism host name depending on configuration'() {
        given:
        RestService restService = new RestService(useMock, 'http://mockhost', 'http://realhost')

        expect:
        restService.getHostName() == expectedHostName

        where:
        useMock | expectedHostName
        true    | 'http://mockhost'
        false   | 'http://realhost'
    }

}
