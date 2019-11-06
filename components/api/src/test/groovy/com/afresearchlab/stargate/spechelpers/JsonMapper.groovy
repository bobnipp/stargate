package com.afresearchlab.stargate.spechelpers

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class JsonMapper {
    @Autowired
    ObjectMapper mapper

    @Autowired
    ResourceLoader loader

    JsonNode readContract(String filename) {
        mapper.readTree(loader.getContract(filename))
    }

    JsonNode readFixture(String filename) {
        mapper.readTree(loader.getFixture(filename))
    }

    JsonNode readJson(String json) {
        mapper.readTree(json)
    }

    String writeJson(JsonNode object) {
        mapper.writeValueAsString(object)
    }
}
