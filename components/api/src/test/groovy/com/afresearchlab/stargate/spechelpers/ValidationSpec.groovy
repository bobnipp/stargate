package com.afresearchlab.stargate.spechelpers

import com.fasterxml.jackson.databind.JsonNode
import com.github.fge.jsonschema.main.JsonSchemaFactory
import com.google.gson.Gson
import io.restassured.module.mockmvc.response.MockMvcResponse
import org.springframework.beans.factory.annotation.Autowired

import java.lang.reflect.Type

class ValidationSpec extends IntegrationSpec {
    @Autowired
    JsonMapper mapper

    @Autowired
    Gson gson

    def getJson(String filename) {
        def object = validateJson(filename)
        mapper.writeJson(object)
    }

    def validateJson(String json, String contractFilename) {
        def object = mapper.readJson(json)
        def contract = mapper.readContract(contractFilename)
        validate(object, contract)
    }

    def validateJson(String filename) {
        def object = mapper.readFixture(filename)
        def contract = mapper.readContract(filename)
        validate(object, contract)
    }

    def <T> T deserializeWithValidation(MockMvcResponse result, Type type, String contractFilename) {
        def json = result.asString()
        def object = mapper.readJson(json)
        def contract = mapper.readContract(contractFilename)
        validate(object, contract)

        gson.fromJson(json, type)
    }

    private static validate(JsonNode object, JsonNode contract) {
        def schema = JsonSchemaFactory.byDefault().getJsonSchema(contract)

        def validation = schema.validate(object)
        assert validation.messages.size == 0
        object
    }
}
