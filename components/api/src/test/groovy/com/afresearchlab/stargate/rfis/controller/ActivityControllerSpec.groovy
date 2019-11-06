package com.afresearchlab.stargate.rfis.controller

import com.afresearchlab.stargate.rfis.model.ActivityRequest
import com.afresearchlab.stargate.rfis.model.AttachmentRequest
import com.afresearchlab.stargate.rfis.service.ActivityService
import com.afresearchlab.stargate.spechelpers.ValidationSpec

import static com.afresearchlab.stargate.spechelpers.ApiHelpers.assertSuccess
import static com.afresearchlab.stargate.spechelpers.ApiHelpers.baseRequest

class ActivityControllerSpec extends ValidationSpec {
    def service
    def controller

    def recordActivity = new ActivityRequest(null, "1", 'No Comment', 'someuser', '1538500400694', [new AttachmentRequest('test.txt', 'bytePackage', 'size string')])

    def setup() {
        service = Mock(ActivityService)
        controller = new ActivityController(service)
    }

    def 'creates a activity'() {
        when:
        def result = baseRequest(controller)
            .body(recordActivity)
            .when()
            .post('/api/v1/activities')

        then:
        assertSuccess(result)
        1 * service.create(recordActivity)
    }

    def 'deletes an activity'() {
        when:
        def result = baseRequest(controller)
            .when()
            .delete('api/v1/activities?id=1')

        then:
        assertSuccess(result)
        1 * service.deleteById(1)
    }
}
