package com.afresearchlab.stargate.appconfig

import spock.lang.Specification

import static com.afresearchlab.stargate.spechelpers.ApiHelpers.assertSuccess
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given

class AppConfigControllerSpec extends Specification {

    def 'it returns Environment Variable by key'() {
        given:
        def controller = new AppConfigController()

        expect:
        def result = given()
            .standaloneSetup(controller)
            .auth()
            .principal({ _ -> 'ScHwARzEnEgGeR' }())
            .contentType('text/html')
            .when()
            .get('/api/v1/config/HOME')
        assertSuccess(result)

        result.asString().contains("/")
    }
}
