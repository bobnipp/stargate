package com.afresearchlab.stargate.lists


import spock.lang.Specification

import static com.afresearchlab.stargate.spechelpers.ApiHelpers.assertSuccess
import static com.afresearchlab.stargate.spechelpers.ApiHelpers.baseRequest

class ListsControllerSpec extends Specification {
    def service
    def controller

    def listCustomLists = [new CustomList('Foo', [])]
    def customList = new CustomList('Foo', [])
    def record = new RecordIdentifier('Bar', 'IMM')

    def setup() {
        service = Mock(ListsService)
        controller = new ListsController(service)
    }

    def 'getLists returns all lists'() {
        given:

        1 * service.getLists() >> listCustomLists

        expect:
        def result = baseRequest(controller)
            .when()
            .get('/api/v1/lists')
        assertSuccess(result)
    }

    def 'create saves a new list'() {
        given:

        1 * service.createList(customList)

        expect:
        def result = baseRequest(controller)
            .body(customList)
            .when()
            .post('/api/v1/lists')
        assertSuccess(result)
    }

    def 'adds records to list'() {
        given:

        1 * service.addRecordToList('Foo', record)

        expect:
        def result = baseRequest(controller)
                .body(record)
                .when()
                .post('/api/v1/lists/Foo')
        assertSuccess(result)
    }

    def 'removes record from list'() {
        given:

        1 * service.removeRecordFromList('Foo', new RecordIdentifier(record.recordType, record.recordId))

        expect:
        def result = baseRequest(controller)
            .when()
            .delete("/api/v1/lists/Foo/${record.recordId}/${record.recordType}")
        assertSuccess(result)
    }

    def 'deletes a list'() {
        when:
        def result = baseRequest(controller)
                .when()
                .delete('api/v1/lists?name=listName')

        then:
        assertSuccess(result)
        1 * service.deleteList("listName")
    }

    def 'updates a list'() {
        given:
        customList.records = [record]
        1 * service.updateList(customList)

        expect:
        def result = baseRequest(controller)
            .body(customList)
            .when()
            .put('/api/v1/lists')
        assertSuccess(result)
    }
}
