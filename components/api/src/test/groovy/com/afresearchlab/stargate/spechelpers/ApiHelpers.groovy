package com.afresearchlab.stargate.spechelpers

import com.afresearchlab.stargate.rfis.model.Rfi
import com.google.gson.Gson
import io.restassured.module.mockmvc.response.MockMvcResponse
import org.hamcrest.Matcher

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given
import static org.hamcrest.Matchers.is
import static org.hamcrest.Matchers.isOneOf

class ApiHelpers {
    static createIssue(controllerSetup, request) {
        def result = controllerSetup
            .body(request)
            .when()
            .post('/api/v1/rfis')
        assertSuccess(result)

        def responseBody = new Gson().fromJson(result.asString(), Rfi.class)
        return responseBody.id
    }

    static baseRequest(controller) {
        given()
            .standaloneSetup(controller)
            .auth()
            .principal({ _ -> 'ScHwARzEnEgGeR' }())
            .contentType('application/json')
    }

    static assertSuccess(MockMvcResponse result) {
        result
            .then()
            .statusCode(is(SUCCESS))
    }

    private static final Matcher<Integer> SUCCESS = isOneOf(200, 201, 202, 204)
}
