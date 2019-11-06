package com.afresearchlab.stargate.rfis.controller


import com.afresearchlab.stargate.rfis.requests.CreateRecordLinkRequest
import com.afresearchlab.stargate.rfis.service.LinkService
import com.afresearchlab.stargate.spechelpers.ValidationSpec

import static com.afresearchlab.stargate.spechelpers.ApiHelpers.assertSuccess
import static com.afresearchlab.stargate.spechelpers.ApiHelpers.baseRequest

class LinkControllerSpec extends ValidationSpec {
    def service
    def controller

    def createLinkRequest = new CreateRecordLinkRequest('123', 'IMM', '321', 'PRISM')

    def setup() {
        service = Mock(LinkService)
        controller = new LinkController(service)
    }

    def 'creates a link'() {
        when:
        def result = baseRequest(controller)
            .body(getJson('rfi/create-rfi-link.request'))
            .when()
            .post('/api/v1/recordlinks')

        then:
        assertSuccess(result)
        1 * service.create(createLinkRequest)
    }

    def 'deletes a link'() {
        when:
        def result = baseRequest(controller)
            .when()
            .delete('api/v1/recordlinks?record1Id=1&record2Id=2&record1System=IMM&record2System=PRISM')

        then:
        assertSuccess(result)
        1 * service.delete('1', '2', 'IMM', 'PRISM')
    }
}
