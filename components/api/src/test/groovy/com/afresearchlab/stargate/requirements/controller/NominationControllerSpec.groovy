package com.afresearchlab.stargate.requirements.controller


import com.afresearchlab.stargate.requirements.model.ImmFullNom
import com.afresearchlab.stargate.requirements.model.NomCr
import com.afresearchlab.stargate.requirements.service.PrismService
import com.afresearchlab.stargate.rfis.model.SelectOption
import com.afresearchlab.stargate.rfis.service.SelectOptionsService
import com.afresearchlab.stargate.spechelpers.ValidationSpec
import com.google.gson.reflect.TypeToken

import static com.afresearchlab.stargate.spechelpers.ApiHelpers.assertSuccess
import static com.afresearchlab.stargate.spechelpers.ApiHelpers.baseRequest

class NominationControllerSpec extends ValidationSpec {
    def NOMINATION_LIST_TYPE = new TypeToken<List<NomCr>>() {}.getType()
    def NOMINATION_TYPE = new TypeToken<ImmFullNom>() {}.getType()

    def controller
    def service
    def optionsService

    def setup() {
        service = Mock(PrismService)
        optionsService = Mock(SelectOptionsService)
        controller = new NominationController(service, optionsService)
    }

    def 'getAllNominations returns all Nominations'() {
        given:
        def nominations = [
                NomCr.builder().id('1').title('title').status('Working').createdOn('2017-12-01T23:43:12.123Z').targets(Collections.emptyList()).build(),
                NomCr.builder().id('2').title('title').status('Working').createdOn('2017-12-01T23:43:12.123Z').targets(Collections.emptyList()).build()
        ]
        1 * service.getAllNominations() >> nominations

        expect:
        def result = baseRequest(controller)
            .when()
            .get('/api/v1/prism/nominations')
        assertSuccess(result)

        gson.fromJson(result.asString(), NOMINATION_LIST_TYPE) == nominations
    }

    //TODO?
    def 'getNomination returns nomination'() {
        given:
        def nomination = ImmFullNom.builder().id('1').title('Cool title bro').status('Working').build()
        1 * service.getNominationById('nomId') >> nomination

        expect:
        def result = baseRequest(controller)
            .when()
            .get('/api/v1/prism/nominations/nomId')
        assertSuccess(result)

        gson.fromJson(result.asString(), NOMINATION_TYPE) == nomination
    }

    def 'getOptions should retrieve rfi options'() {
        given:
        def expectedOptions = [
            "foo": [new SelectOption(1, "Bar"), new SelectOption(2, "Baz")],
            "bar": [new SelectOption(1, "Quo"), new SelectOption(2, "Qux")]
        ]
        1 * optionsService.getNomOptions() >> expectedOptions

        when:
        def response = baseRequest(controller)
            .when()
            .get("/api/v1/prism/options")

        then:
        assertSuccess(response)
        response.asString() == gson.toJson(expectedOptions)
    }

}
