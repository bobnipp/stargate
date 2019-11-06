package com.afresearchlab.stargate.spechelpers

import io.restassured.RestAssured
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class IntegrationSpec extends Specification {
    @Autowired
    JdbcTemplate jdbcTemplate

    def setup() {
        RestAssured.port = 8081
    }

    def cleanup() {
        //delete data
        jdbcTemplate.update("DELETE FROM users")
        jdbcTemplate.update("DELETE FROM rfi_activities")
        jdbcTemplate.update("DELETE FROM imm_rfis")
        jdbcTemplate.update('DELETE FROM imm_links')
        jdbcTemplate.update('DELETE FROM record_history')
        jdbcTemplate.update('DELETE FROM lists')
        jdbcTemplate.update('DELETE FROM targets')
        jdbcTemplate.update('DELETE FROM sensors')

        //reset ID counter
        jdbcTemplate.update("ALTER TABLE imm_rfis AUTO_INCREMENT = 1")
    }
}