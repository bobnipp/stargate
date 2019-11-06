package com.afresearchlab.stargate.rfis.controller

import com.afresearchlab.stargate.rfis.service.TargetService
import com.afresearchlab.stargate.spechelpers.ValidationSpec

import static com.afresearchlab.stargate.spechelpers.ApiHelpers.assertSuccess
import static com.afresearchlab.stargate.spechelpers.ApiHelpers.baseRequest

class TargetControllerSpec extends ValidationSpec {
    def service
    def controller

    def setup() {
        service = Mock(TargetService)
        controller = new TargetController(service)
    }

    def 'deletes a target'() {
        when:
        def result = baseRequest(controller)
            .when()
            .delete('api/v1/targets?id=1')

        then:
        assertSuccess(result)
        1 * service.deleteById(1)
    }
}