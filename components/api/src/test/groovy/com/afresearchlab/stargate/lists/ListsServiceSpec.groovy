package com.afresearchlab.stargate.lists

import spock.lang.Specification

class ListsServiceSpec extends Specification {
    def repository
    def service

    def setup() {
        repository = Mock(ListsRepository)
        service = new ListsService(repository)
    }

    def 'getLists returns all lists from the repository'() {
        given:
        def lists = [
            new CustomList(name: 'foo'),
            new CustomList(name: 'bar'),
        ]

        1 * repository.getLists() >> lists

        when:
        def result = service.getLists()

        then:
        result == lists
    }

    def 'createLists saves a new list to the repository'() {
        given:
        def customList = new CustomList(name: 'add')

        when:
        service.createList(customList)

        then:
        1 * repository.createList(customList)
    }

    def 'addRecordToList saves a record to a list'() {
        when:
        service.addRecordToList('foo', new RecordIdentifier('a', 'b'))

        then:
        1 * repository.addRecordToList('foo', new RecordIdentifier('a', 'b'))
    }

    def 'deletes a list'() {
        when:
        service.deleteList("this")

        then:
        1 * repository.deleteList("this")
    }

    def 'removeRecordFromList removes a record from a list'() {
        when:
        service.removeRecordFromList('foo', new RecordIdentifier('a', 'b'))

        then:
        1 * repository.removeRecordFromList('foo', new RecordIdentifier('a', 'b'))
    }

    def 'update list updates the list records'() {
        given:
        def customList = new CustomList(name: 'add', records: [new RecordIdentifier('a', 'b')])

        when:
        service.updateList(customList)

        then:
        1 * repository.updateList(customList)
    }
}
