package com.afresearchlab.stargate.lists

import com.afresearchlab.stargate.spechelpers.IntegrationSpec
import org.springframework.beans.factory.annotation.Autowired

class ListsRepositorySpec extends IntegrationSpec {

    def list = new CustomList("pokemon", [])
    def otherList = new CustomList("pokemon-gyms", [])

    @Autowired ListsRepository subject

    def 'getLists returns all lists'() {
        given:
        subject.createList(list)
        subject.createList(otherList)

        when:
        def result = subject.getLists()

        then:
        result == [list, otherList]
    }

    def 'addRecordToList adds a record to a list'() {
        given:
        subject.createList(list)

        when:
        subject.addRecordToList("pokemon", new RecordIdentifier('SYS', 'charizard'))
        def result = subject.getLists()

        then:
        result == [new CustomList("pokemon", [new RecordIdentifier('SYS', 'charizard')])]
    }

    def 'addRecordToList does not add duplicates to list'() {
        given:
        subject.createList(list)

        when:
        subject.addRecordToList("pokemon", new RecordIdentifier('SYS', 'charizard'))
        subject.addRecordToList("pokemon", new RecordIdentifier('SYS', 'charizard'))
        subject.addRecordToList("pokemon", new RecordIdentifier('SYS', 'squirtle'))
        subject.addRecordToList("pokemon", new RecordIdentifier('OTHER', 'charizard'))
        def result = subject.getLists()

        then:
        result == [new CustomList("pokemon", [
                new RecordIdentifier('SYS', 'charizard'),
                new RecordIdentifier('SYS', 'squirtle'),
                new RecordIdentifier('OTHER', 'charizard')
        ])]
    }

    def 'removeRecordFromAllLists removes the record from all lists'() {
        given:
        subject.createList(list)
        subject.createList(otherList)

        subject.addRecordToList("pokemon", new RecordIdentifier('SYS', 'charizard'))
        subject.addRecordToList("pokemon", new RecordIdentifier('SYS', 'squirtle'))
        subject.addRecordToList("pokemon", new RecordIdentifier('OTHER', 'charizard'))

        subject.addRecordToList("pokemon-gyms", new RecordIdentifier('SYS', 'squirtle'))

        when:
        subject.removeRecordFromAllLists(new RecordIdentifier('SYS', 'squirtle'))
        def result = subject.getLists()

        then:
        result == [new CustomList("pokemon", [
            new RecordIdentifier('SYS', 'charizard'),
            new RecordIdentifier('OTHER', 'charizard')
        ]),
        new CustomList("pokemon-gyms", [])
        ]
    }

    def 'removeRecordFromList removes the record from the specified list'() {
        given:
        subject.createList(list)
        subject.createList(otherList)

        subject.addRecordToList("pokemon", new RecordIdentifier('SYS', 'charizard'))
        subject.addRecordToList("pokemon", new RecordIdentifier('SYS', 'squirtle'))
        subject.addRecordToList("pokemon", new RecordIdentifier('OTHER', 'charizard'))

        subject.addRecordToList("pokemon-gyms", new RecordIdentifier('SYS', 'squirtle'))

        when:
        subject.removeRecordFromList('pokemon', new RecordIdentifier('SYS', 'squirtle'))
        def result = subject.getLists()

        then:
        result == [new CustomList("pokemon", [
            new RecordIdentifier('SYS', 'charizard'),
            new RecordIdentifier('OTHER', 'charizard')
        ]), new CustomList("pokemon-gyms", [new RecordIdentifier('SYS', 'squirtle')])
        ]
    }

    def 'updateList updates the records in the list'() {
        given:
        subject.createList(list)

        subject.addRecordToList("pokemon", new RecordIdentifier('SYS', 'charizard'))
        subject.addRecordToList("pokemon", new RecordIdentifier('SYS', 'squirtle'))

        when:
        def updateList = subject.getLists()[0]
        updateList.getRecords().add(new RecordIdentifier('OTHER', 'charizard'))
        subject.updateList(updateList)
        def result = subject.getLists()

        then:
        result == [new CustomList("pokemon", [
            new RecordIdentifier('SYS', 'charizard'),
            new RecordIdentifier('SYS', 'squirtle'),
            new RecordIdentifier('OTHER', 'charizard')
        ])]
    }
}
