package com.afresearchlab.stargate.healthcheck

import com.afresearchlab.stargate.requirements.service.PrismService
import com.afresearchlab.stargate.rfis.service.RfiService
import com.afresearchlab.stargate.spechelpers.ValidationSpec
import com.google.gson.Gson

import static com.afresearchlab.stargate.spechelpers.ApiHelpers.baseRequest

class HealthCheckControllerSpec extends ValidationSpec {
    def controller
    def prismService
    def rfiService

    def setup() {
        prismService = Mock(PrismService)
        rfiService = Mock(RfiService)
        controller = new HealthCheckController(prismService, rfiService)
    }

    def 'healthCheck returns success'() {
        given:
        1 * prismService.healthCheck() >> new HealthCheck(true, 9999)

        when:
        def response = baseRequest(controller)
            .when()
            .get('/api/v1/health/prism')

        then:
        def json = new Gson().fromJson(response.asString(), HealthCheck.class)
        json.success
    }

    def 'healthCheck returns failure due to failed health check'() {
        given:
        1 * prismService.healthCheck() >> new HealthCheck(false, -1)

        when:
        def response = baseRequest(controller)
            .when()
            .get('/api/v1/health/prism')

        then:
        def json = new Gson().fromJson(response.asString(), HealthCheck.class)
        !json.success
    }

    def 'healthCheck returns failure due to non-existent integration name'() {
        given:
        0 * prismService.healthCheck()  >> new HealthCheck(false, -1)

        when:
        def response = baseRequest(controller)
            .when()
            .get('/api/v1/health/failure')

        then:
        def json = new Gson().fromJson(response.asString(), HealthCheck.class)
        !json.success
    }

}
