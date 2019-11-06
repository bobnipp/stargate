package com.afresearchlab.stargate.filter

import spock.lang.Specification

import static com.afresearchlab.stargate.spechelpers.ApiHelpers.assertSuccess
import static com.afresearchlab.stargate.spechelpers.ApiHelpers.baseRequest

class FiltersControllerSpec extends Specification {
    def service
    def controller

    def filters = new Filters([], [], [], [], [], '')

    def setup() {
        service = Mock(FiltersService)
        controller = new FiltersController(service)
    }

    def "get filters for user returns the filters"() {
        given:
        1 * service.getFiltersByUser('stargate') >> filters

        expect:
        def result = baseRequest(controller)
            .standaloneSetup(controller)
            .auth()
            .principal({ _ -> 'stargate' }())
            .when()
            .get('/api/v1/filters')
        assertSuccess(result)
    }

    def "save filters for user saves the filters"() {
        given:
        1 * service.saveFiltersByUser('stargate', filters)

        expect:
        def result = baseRequest(controller)
            .body(filters)
            .standaloneSetup(controller)
            .auth()
            .principal({ _ -> 'stargate' }())
            .when()
            .post('/api/v1/filters')
        assertSuccess(result)
    }
}
