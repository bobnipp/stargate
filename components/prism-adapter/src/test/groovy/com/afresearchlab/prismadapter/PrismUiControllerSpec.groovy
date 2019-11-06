package com.afresearchlab.prismadapter

import com.afresearchlab.prismadapter.controller.PrismUiController
import com.afresearchlab.prismadapter.model.PrismUiNomination
import com.afresearchlab.prismadapter.service.PrismUiService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given

class PrismUiControllerSpec extends IntegrationSpec {

    def controller
    def prismUiService

    def setup() {
        prismUiService = Mock(PrismUiService)
        controller = new PrismUiController(prismUiService)
    }

    def 'getAllNominations() responds with nominations'() {
        given:

        def nom1 = new PrismUiNomination()
        nom1.key = 'key1'
        nom1.name = 'name1'

        def nom2 = new PrismUiNomination()
        nom2.key = 'key2'
        nom2.name = 'name2'

        1 * prismUiService.getAllNominations() >> [nom1, nom2]

        expect:
        def result = given()
            .standaloneSetup(controller)
            .when()
            .get('/ui/nominations')

        result
            .then()
            .statusCode(200)

        result.asString() == new ObjectMapper().valueToTree([nom1, nom2]).toString()
    }
}
