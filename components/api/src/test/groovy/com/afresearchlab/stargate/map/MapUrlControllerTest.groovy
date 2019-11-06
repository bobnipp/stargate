package com.afresearchlab.stargate.map


import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class MapUrlControllerTest extends Specification {
    @Autowired
    ObjectMapper objectMapper

    MapUrlController controller

    def setup() {
        controller = new MapUrlController("mow.example.com")
    }

    def 'get map of the world url'() {
        when:
        def result = controller.getMapUrl()

        then:
        assert result == new MapUrlResponse("mow.example.com")
    }
}
