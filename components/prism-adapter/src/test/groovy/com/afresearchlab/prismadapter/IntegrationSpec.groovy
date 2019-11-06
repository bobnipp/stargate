package com.afresearchlab.prismadapter

import io.restassured.RestAssured
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class IntegrationSpec extends ValidationSpec {

    def setup() {
        RestAssured.port = 8082
    }
}
