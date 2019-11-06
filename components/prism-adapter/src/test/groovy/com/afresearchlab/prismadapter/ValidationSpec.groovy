package com.afresearchlab.prismadapter

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.fge.jsonschema.main.JsonSchemaFactory
import spock.lang.Specification

class ValidationSpec extends Specification {

    def getJson(String filename) {
        def mapper = new ObjectMapper()
        def json = validateJson(filename, mapper)
        mapper.writeValueAsString(json)
    }

    def validateJson(String json, String contractFilename, ObjectMapper mapper = new ObjectMapper()) {
        def object = mapper.readTree(json)
        def contract = mapper.readTree(getFile('contracts/prism/' + contractFilename))
        validate(object, contract)
    }

    def validateJson(String filename, ObjectMapper mapper = new ObjectMapper()) {
        def object = mapper.readTree(getFile('fixtures/prism/' + filename))
        def contract = mapper.readTree(getFile('contracts/prism/' + filename))
        validate(object, contract)
    }

    private validate(JsonNode object, JsonNode contract) {
        def schema = JsonSchemaFactory.byDefault().getJsonSchema(contract)

        def validation = schema.validate(object)
        assert validation.messages.size == 0
        object
    }

    private getFile(String filename) {
        def loader = getClass().getClassLoader()
        new File(loader.getResource(filename).getFile())
    }
}
