package com.afresearchlab.stargate.rfis.controller

import com.afresearchlab.stargate.rfis.service.SensorService
import com.afresearchlab.stargate.spechelpers.ValidationSpec

import static com.afresearchlab.stargate.spechelpers.ApiHelpers.assertSuccess
import static com.afresearchlab.stargate.spechelpers.ApiHelpers.baseRequest

class SensorControllerSpec extends ValidationSpec {
    def service
    def controller

    def setup() {
        service = Mock(SensorService)
        controller = new SensorController(service)
    }

    def 'deletes a sensor'() {
        when:
        def result = baseRequest(controller)
            .when()
            .delete('api/v1/sensors?id=1')

        then:
        assertSuccess(result)
        1 * service.deleteById(1)
    }
}
