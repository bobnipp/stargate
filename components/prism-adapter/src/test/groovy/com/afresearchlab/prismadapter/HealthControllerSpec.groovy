package com.afresearchlab.prismadapter

import com.afresearchlab.prismadapter.controller.HealthController
import com.afresearchlab.prismadapter.model.HealthCheck
import com.google.gson.Gson
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given

class HealthControllerSpec extends IntegrationSpec {

    def controller
    def jedisPool
    def jedisClient

    def setup() {
        jedisPool = Mock(JedisPool)
        jedisClient = Mock(Jedis)
        controller = new HealthController(jedisPool)
    }

    def 'get /healthcheck returns 200 and true when prism is reachable'() {
        given:
        1 * jedisPool.getResource() >> jedisClient
        1 * jedisClient.ping() >> 'PONG'
        1 * jedisClient.get('last_save') >> "10000"

        when:
        def result = given()
            .standaloneSetup(controller)
            .contentType('application/json')
            .when()
            .get('/healthcheck')

        then:
        result
            .then()
            .statusCode(200)

        def json = new Gson().fromJson(result.asString(), HealthCheck.class)
        json.success
    }

    def 'get /healthcheck returns 200 and false when prism is unreachable'() {
        given:
        1 * jedisPool.getResource() >> jedisClient
        1 * jedisClient.ping() >> null

        when:
        def result = given()
            .standaloneSetup(controller)
            .contentType('application/json')
            .when()
            .get('/healthcheck')

        then:
        result
            .then()
            .statusCode(200)

        def json = new Gson().fromJson(result.asString(), HealthCheck.class)
        !json.success
    }

}
