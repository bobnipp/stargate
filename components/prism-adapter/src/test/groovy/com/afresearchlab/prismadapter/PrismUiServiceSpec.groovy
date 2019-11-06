package com.afresearchlab.prismadapter

import com.afresearchlab.prismadapter.model.PrismUiNomination
import com.afresearchlab.prismadapter.service.PrismUiService
import com.afresearchlab.prismadapter.service.RestService
import com.afresearchlab.prismadapter.service.SessionManager
import org.springframework.http.*
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.response.MockRestResponseCreators
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import javax.inject.Provider

import static org.springframework.http.HttpMethod.GET
import static org.springframework.http.HttpMethod.POST
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess

class PrismUiServiceSpec extends Specification {
    def restService = new RestService(true, 'http://mockhost', 'http://realhost')
    def mockSessionCheck = Mock(SessionManager)
    def mockServer = MockRestServiceServer.createServer(restService.getRestTemplate())
    def data = '{"data": [{"disabled": false, "savable": true, "leaf": true, "editable": true, "expanded": false, "iconCls": "prism-nom-icon", "clickable": true, "selfLoad": true, "data": {"recordId": "000000000001303PR7AWS", "recordName": "1000", "prismState": "A", "type": "NOM", "prismStatus": ""}, "text": "1000(1000)", "id": "0-000000000001303PR7AWS"}, {"disabled": false, "savable": true, "leaf": true, "editable": true, "expanded": false, "iconCls": "prism-nom-icon", "clickable": true, "selfLoad": true, "data": {"recordId": "000000000001302PR7AWS", "recordName": "1234", "prismState": "A", "type": "NOM", "prismStatus": ""}, "text": "1234(1234)", "id": "0-000000000001302PR7AWS"}], "success": true}'
    def subject

    def setup() {
        def mockCometClientProvider = Mock(Provider) {
            get() >> Mock(RealPrismCometClient) {
                getNomList('sessionId') >> data
            }
        }

        subject = new PrismUiService(mockCometClientProvider, restService, 'PRISM_UI_USERNAME', 'PRISM_UI_PASSWORD', mockSessionCheck)
    }

    def 'calls prism to return a successful health check'() {
        given:
        mockServer
            .expect(requestTo('http://mockhost/prism-webapp/'))
            .andExpect(method(GET))
            .andRespond(withSuccess())

        when:
        subject.prismHealth()

        then:
        noExceptionThrown()
    }

    def 'calls prism to return a failed health check'() {
        given:
        mockServer
            .expect(requestTo('http://mockhost/prism-webapp/'))
            .andExpect(method(GET))
            .andRespond(withBadRequest())

        when:
        subject.prismHealth()

        then:
        thrown ServiceUnavailableException
    }

    def 'getNominationHistory() returns nomination history'() {
        given:
        setupLoginMock()

        def historyJson = '{"data":[{"action":"Update","modifiedOn":"11/01/2018 11:46:53","modifiedBy":"CFREKING (cfreking)"},{"action":"Update","modifiedOn":"11/01/2018 11:46:13","modifiedBy":"JGUSTINE (jgustine)"},{"action":"Insert","modifiedOn":"11/01/2018 11:45:30","modifiedBy":"JROSSMEISL (jrossmeisl)"}],"success":true}'

        def nom = new PrismUiNomination()
        nom.key = '000000000001303PR7AWS'

        mockServer
            .expect(requestTo('http://mockhost/prism-webapp/rest/system/history/NOM/' + nom.key))
            .andExpect(method(GET))
            .andRespond(withSuccess(historyJson, MediaType.APPLICATION_JSON))

        when:
        def result = subject.getNominationHistory(nom.key)

        then:
        result.username == ['cfreking', 'jgustine', 'jrossmeisl']
        result.action == ['Update', 'Update', 'Insert']
        result.date == ['11/01/2018 11:46:53', '11/01/2018 11:46:13', '11/01/2018 11:45:30']
    }

    def 'getAllNominations() returns nominations'() {
        given:
        setupLoginMock()

        def nom1 = new PrismUiNomination()
        nom1.key = '000000000001303PR7AWS'
        nom1.name = '1000'

        def nom2 = new PrismUiNomination()
        nom2.key = '000000000001302PR7AWS'
        nom2.name = '1234'

        when:
        def result = subject.getAllNominations()

        then:
        result == [nom1, nom2]
    }

    def 'getSessionId() logs in and returns session Id'() {
        given:
        setupLoginMock()

        when:
        def result = subject.getSessionId()

        then:
        result == 'sessionId'
    }

    def 'session id is cached'() {
        given:
        def headers = new HttpHeaders()
        headers.set('Set-Cookie', 'JSESSIONID=sessionId;')

        def mockRestTemplate = Mock(RestTemplate)

        def mockRestService = Mock(RestService) {
            getRestTemplate() >> mockRestTemplate
        }

        def prismUIService = new PrismUiService(null, mockRestService, 'PRISM_UI_USERNAME', 'PRISM_UI_PASSWORD', new SessionManager(100))

        when:
        prismUIService.getSessionId()
        prismUIService.getSessionId()

        then:
        1 * mockRestTemplate.postForEntity(_ as String, _, String.class) >> new ResponseEntity<String>(headers, HttpStatus.FOUND)
    }

    def 'getSessionId() updates session time counter after session id retrieval'() {
        given:
        setupLoginMock()

        when:
        subject.getSessionId()

        then:
        1 * mockSessionCheck.updateSessionStart()
    }

    def setupLoginMock() {
        mockSessionCheck.isSessionValid(_ as String) >> false

        HttpHeaders headers = new HttpHeaders()
        headers.add('Set-Cookie', 'sessionId')

        mockServer
            .expect(requestTo('http://mockhost/prism-webapp/j_spring_security_check'))
            .andExpect(method(POST))
            .andRespond(
            MockRestResponseCreators
                .withStatus(HttpStatus.FOUND)
                .headers(headers))
    }
}
