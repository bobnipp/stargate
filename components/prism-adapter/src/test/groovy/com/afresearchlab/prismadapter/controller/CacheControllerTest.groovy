package com.afresearchlab.prismadapter.controller

import com.afresearchlab.prismadapter.service.PrismCacheDataJammer
import spock.lang.Specification

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given

class CacheControllerTest extends Specification {

    def subject

    def cacheJammer

    def setup() {
        cacheJammer = Mock(PrismCacheDataJammer)
        subject = new CacheController(cacheJammer)
    }

    def 'POST /cache builds the prism cache'() {
        when:
        def result = given()
            .standaloneSetup(subject)
            .body('')
            .when()
            .post('/cache')

        then:
        result
            .then()
            .statusCode(200)

        1 * cacheJammer.jamIt()
    }

    def 'DELETE /cache deletes the prism cache'() {
        when:
        def result = given()
            .standaloneSetup(subject)
            .body('')
            .when()
            .delete('/cache')

        then:
        result
            .then()
            .statusCode(200)

        1 * cacheJammer.deleteIt()
    }
}
