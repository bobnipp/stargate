package com.afresearchlab.stargate.requirements.persistence

import com.afresearchlab.prismadaptermodels.FullNom
import com.afresearchlab.stargate.spechelpers.ValidationSpec
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.web.client.RestTemplate
import spock.lang.Unroll

import static org.springframework.http.HttpMethod.GET
import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess

class PrismRepositorySpec extends ValidationSpec {
    def restTemplate = new RestTemplate()
    def mockServer = MockRestServiceServer.createServer(restTemplate)
    def subject

    def setup() {
        subject = new PrismRepository(restTemplate, 'http://mockhost')
    }

    @Unroll
    def 'calls prism adapter and retrieves nomination by id'() {
        given:
        def json = '{"id": "'+ givenNomId + '", "status": "nomStatus", "title": "nomTitle", "targets": []}'
        mockServer
                .expect(requestTo("http://mockhost/nominations/${expectedUrlEncodedNomId}" ))
                .andExpect(method(GET))
                .andRespond(withSuccess(json, APPLICATION_JSON))

        when:
        def nom = subject.getNominationById(givenNomId)

        then:
        def targets = Collections.emptyList()
        nom == FullNom.builder().id(expectedDeserializedNomId).title("nomTitle").status("nomStatus").targets(targets).build()

        where:
        givenNomId            | expectedDeserializedNomId | expectedUrlEncodedNomId
        "nomId"               | "nomId"                   | "nomId"
        "nomId#withSomeStuff" | "nomId#withSomeStuff"     | "nomId%23withSomeStuff"
        "nomId withSomeStuff" | "nomId withSomeStuff"     | "nomId%20withSomeStuff"
    }

    def 'handles missing nominations'() {
        given:
        mockServer
                .expect(requestTo('http://mockhost/nominations/BAD_ID' ))
                .andExpect(method(GET))
                .andRespond(withServerError())

        when:
        def nom = subject.getNominationById("BAD_ID")

        then:
        nom == null
    }

    def 'calls prism adapter to retreive all prism nominations'() {
        given:
        def json = '[{"id": "nomId", "name": "nomName1", "dataInfo": {"createdOn": "2017-12-01T23:43:12.123Z"}, "nomImint": { "respOrg": "ACC 01", "status": "APPROVE"}}]'

        mockServer
                .expect(requestTo('http://mockhost/nominations'))
                .andExpect(method(GET))
                .andRespond(withSuccess(json, APPLICATION_JSON))

        when:
        def all = subject.getAllNominations()

        then:
        all.size() == 1
        all.id == ['nomId']
        all.name == ['nomName1']
        all[0].dataInfo.createdOn.toString() == '2017-12-01T23:43:12.123Z'
        all[0].nomImint.respOrg == 'ACC 01'
        all[0].nomImint.status.toString() == 'APPROVE'
    }

    def 'calls prism adapter health check'() {
        given:
        def json = '{"success": true, "lastsave": 999999}'
        mockServer
                .expect(requestTo('http://mockhost/healthcheck'))
                .andExpect(method(GET))
                .andRespond(withSuccess(json, APPLICATION_JSON))

        when:
        def response = subject.healthCheck()

        then:
        response.success
    }

    def 'calls prism adapter health check with failed health'() {
        given:
        def json = '{"success": false, "lastsave": -1}'
        mockServer
                .expect(requestTo('http://mockhost/healthcheck'))
                .andExpect(method(GET))
                .andRespond(withSuccess(json, APPLICATION_JSON))

        when:
        def response = subject.healthCheck()

        then:
        !response.success
    }
}
