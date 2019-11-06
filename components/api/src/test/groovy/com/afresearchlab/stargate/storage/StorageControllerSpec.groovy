package com.afresearchlab.stargate.storage

import com.afresearchlab.stargate.spechelpers.ValidationSpec

import static com.afresearchlab.stargate.spechelpers.ApiHelpers.assertSuccess
import static com.afresearchlab.stargate.spechelpers.ApiHelpers.baseRequest

class StorageControllerSpec extends ValidationSpec {

    def service
    def controller

    def setup() {
        service = Mock(StorageService)
        controller = new StorageController(service)
    }

    def 'gets a file'() {
        given:
        1 * service.get('recordId', 'filename')

        expect:
        def result = baseRequest(controller)
            .when()
            .get('/api/v1/storage/recordId/filename')
        assertSuccess(result)
    }
}
