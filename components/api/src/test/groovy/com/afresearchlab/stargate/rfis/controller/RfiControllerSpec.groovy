package com.afresearchlab.stargate.rfis.controller

import com.afresearchlab.prismadaptermodels.RecordHistory
import com.afresearchlab.stargate.rfis.model.Activity
import com.afresearchlab.stargate.rfis.model.Link
import com.afresearchlab.stargate.rfis.model.Rfi
import com.afresearchlab.stargate.rfis.model.SelectOption
import com.afresearchlab.stargate.rfis.model.Sensor
import com.afresearchlab.stargate.rfis.model.Target
import com.afresearchlab.stargate.rfis.presenter.RfiListItem
import com.afresearchlab.stargate.rfis.service.RfiService
import com.afresearchlab.stargate.rfis.service.SelectOptionsService
import com.afresearchlab.stargate.spechelpers.ValidationSpec
import com.google.gson.reflect.TypeToken

import static com.afresearchlab.stargate.spechelpers.ApiHelpers.assertSuccess
import static com.afresearchlab.stargate.spechelpers.ApiHelpers.baseRequest

class RfiControllerSpec extends ValidationSpec {

    def RFI_LIST_TYPE = new TypeToken<List<RfiListItem>>() {}.getType()

    def service
    def optionsService
    def controller

    def rfi1 = new Rfi(1L, 'Do this', 'CA', '1.0 2.0', 'thecity', 'theprovince', 'nai', 'IMMEDIATE', 'TO_DO', 'descriptive things', 'objective things', 1, 1, 1, 'custom classification', 1, 1, 'signature', 1, 1, 1, 'operation', '12 Aug 2019 13:00', '15 Nov 2019 13:00', 'collection term', 'collection type', 1, 'assignee', 'collection guidance', ['eei1', 'eei2'], [new Link('parentObjectId', 'linkedObjectId', 'record1System', 'system2', 'title', 'status')], [new Activity(1, "1", "comment text", "user1", "1538500400694", [])], [new Target(1, 1, 'name', 'Point', 1.0, 'km', '1.0 2.0')], [new Sensor(1, 1, 'sensor 1', 'sensor type 1', 'mode 1', 1, 2)], [new RecordHistory('user', 'action', '2019-12-12')], '2018-12-12')
    def rfi2 =  new Rfi(2L, 'Do this too', 'US', '1.0 2.0', 'theothercity', 'theotherprovince', 'theothernai', 'IMMEDIATE', 'TO_DO', 'descriptive things', 'objective things', 2, 2, 2, 'custom classification 2', 2, 2, 'signature 2', 2, 2, 2, 'operation 1', '12 Aug 2019 13:00', '15 Nov 2019 13:00', 'collection term', 'collection type', 1, 'assignee', 'collection guidance',['eei1', 'eei2'], [new Link('parentObjectId', 'linkedObjectId', 'record1System', 'system2', 'title', 'status')], [new Activity(1, "1", "comment text", "user1", "1538500400694", [])], [new Target(1, 1, 'name', 'Point', 1.0, 'km', '1.0 2.0')], [new Sensor(1, 1, 'sensor 1', 'sensor type 1', 'mode 1', 1, 2)], [new RecordHistory('user', 'action', '2019-11-12')], '2018-12-13')

    def setup() {
        service = Mock(RfiService)
        optionsService = Mock(SelectOptionsService)
        controller = new RfiController(service, optionsService)
    }

    def 'getAll returns all RFIs'() {
        given:
        def expectedListItems = [
            new RfiListItem(rfi1.id, rfi1.title, rfi1.status, rfi1.priority, rfi1.justification, rfi1.coordinates, rfi1.createdOn, [],
                rfi1.city, rfi1.country, rfi1.nai, rfi1.operation,
                rfi1.poc, rfi1.prevResearch, rfi1.region),
            new RfiListItem(rfi2.id, rfi2.title, rfi2.status, rfi2.priority, rfi2.justification, rfi2.coordinates, rfi2.createdOn, [],
                rfi2.city, rfi2.country, rfi2.nai, rfi2.operation,
                rfi2.poc, rfi2.prevResearch, rfi2.region)
        ]

        1 * service.getAll() >> expectedListItems

        expect:
        def result = baseRequest(controller)
            .when()
            .get('/api/v1/imm/rfis')
        assertSuccess(result)

        deserializeWithValidation(result, RFI_LIST_TYPE, 'rfi/rfi-list') == expectedListItems
    }

    def 'creates a new rfi'() {
        given:
        1 * service.create(_, _) >> rfi1

        expect:
        def result = baseRequest(controller)
            .body(getJson('rfi/create-rfi.request'))
            .when()
            .post('/api/v1/imm/rfis')
        assertSuccess(result)

        Rfi response = deserializeWithValidation(result, Rfi.class, 'rfi/rfi')
        response.id == 1
    }

    def 'update updates an rfi'() {
        when:
        def result = baseRequest(controller)
            .body(getJson('rfi/rfi'))
            .when()
            .put("/api/v1/imm/rfis/1")

        then:
        assertSuccess(result)
        1 * service.updateRfi(_, _) >> rfi1
        Rfi response = deserializeWithValidation(result, Rfi.class, 'rfi/rfi')
        response.id == 1
    }

    def 'get returns an rfi'() {
        given:
        1 * service.get(1) >> rfi1

        when:
        def response = baseRequest(controller)
            .body(getJson('rfi/rfi'))
            .when()
            .get("/api/v1/imm/rfis/1")

        then:
        assertSuccess(response)

        deserializeWithValidation(response, Rfi.class, 'rfi/rfi') == rfi1
    }

    def 'delete deletes an rfi'() {
        given:
        1 * service.delete(1)

        when:
        def response = baseRequest(controller)
            .body(getJson('rfi/rfi'))
            .when()
            .delete("/api/v1/imm/rfis/1")

        then:
        assertSuccess(response)
    }

    def 'getOptions should retrieve rfi options'() {
        given:
        def expectedOptions = [
            "foo": [new SelectOption(1, "Bar"), new SelectOption(2, "Baz")],
            "bar": [new SelectOption(1, "Quo"), new SelectOption(2, "Qux")]
        ]
        1 * optionsService.getRfiOptions() >> expectedOptions

        when:
        def response = baseRequest(controller)
            .when()
            .get("/api/v1/imm/options")

        then:
        assertSuccess(response)
        response.asString() == gson.toJson(expectedOptions)
    }
}
