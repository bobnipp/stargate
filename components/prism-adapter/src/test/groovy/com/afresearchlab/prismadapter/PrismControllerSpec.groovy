package com.afresearchlab.prismadapter

import com.afresearchlab.prismadapter.controller.PrismController
import com.afresearchlab.prismadapter.service.PrismAggregateService
import com.afresearchlab.prismadapter.service.PrismCacheDataUnjammer
import com.afresearchlab.prismadapter.service.PrismSOAPService
import com.afresearchlab.prismadaptermodels.FullNom
import com.afresearchlab.prismadaptermodels.IMMPrismTargetType
import com.afresearchlab.prismadaptermodels.RecordHistory
import com.afresearchlab.prismadaptermodels.Target
import com.fasterxml.jackson.databind.ObjectMapper
import com.saic.prism.ws.researchws.prismresearchws.PrismNomIMINTShort
import com.saic.prism.ws.researchws.prismresearchws.PrismNomShort
import com.saic.prism.ws.researchws.prismresearchws.PrismNomStatus
import spock.lang.Specification

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given

class PrismControllerSpec extends Specification {

    def controller
    def cacheUnJammer
    def prismAggregateService

    def setup() {
        cacheUnJammer = Mock(PrismCacheDataUnjammer)
        prismAggregateService = Mock(PrismAggregateService)
        controller = new PrismController(cacheUnJammer)
    }

    def 'GET /nominations responds with all nominations'() {
        given:
        PrismNomIMINTShort prismNomIMINTShort = new PrismNomIMINTShort()
        prismNomIMINTShort.status = PrismNomStatus.APPROVE

        PrismNomShort nom1 = new PrismNomShort()
        nom1.id = 'id1'
        nom1.name = 'name1'
        nom1.nomIMINTShort = prismNomIMINTShort

        PrismNomShort nom2 = new PrismNomShort()
        nom2.id = 'id2'
        nom2.name = 'name2'
        nom2.nomIMINTShort = prismNomIMINTShort

        when:
        def result = given()
            .standaloneSetup(controller)
            .when()
            .get('/nominations')

        then:
        1 * cacheUnJammer.getAllNominations() >> [nom1, nom2]

        result
            .then()
            .statusCode(200)

        new ObjectMapper().valueToTree([nom1, nom2]).toString() == result.asString()
    }

    def 'getNomination() returns with a single nomination'() {
        given:
        def coords = '2.0 3.0, 1.0 1.0, 1.0 2.0, 1.0 1.0'

        def history = [
                RecordHistory.builder().username("Jim").action("Update").date("02 Jan 2018").build(),
                RecordHistory.builder().username("Fred").action("Insert").date("01 Jan 2018").build(),
        ]

        def fullNom = FullNom.builder()
            .id('nomId')
            .status('nomStatus')
            .title('nomTitle')
            .targets(Collections.singletonList(new Target("target", IMMPrismTargetType.DSA, coords)))
            .recordHistory(history)
            .build()

        when:
        def result = given()
            .standaloneSetup(controller)
            .when()
            .get('/nominations/id1')

        then:
        1 * cacheUnJammer.getNomination('id1') >> fullNom
        result
            .then()
            .statusCode(200)
        new ObjectMapper().valueToTree(fullNom).toString() == result.asString()
    }
}