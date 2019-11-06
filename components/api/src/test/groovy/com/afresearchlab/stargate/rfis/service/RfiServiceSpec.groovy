package com.afresearchlab.stargate.rfis.service

import com.afresearchlab.prismadaptermodels.RecordHistory
import com.afresearchlab.stargate.healthcheck.HealthCheck
import com.afresearchlab.stargate.lists.ListsService
import com.afresearchlab.stargate.lists.RecordIdentifier
import com.afresearchlab.stargate.persistence.ActivityRepository
import com.afresearchlab.stargate.persistence.HistoryRepository
import com.afresearchlab.stargate.persistence.RfiRepository
import com.afresearchlab.stargate.persistence.SensorRepository
import com.afresearchlab.stargate.persistence.TargetRepository
import com.afresearchlab.stargate.rfis.model.Activity
import com.afresearchlab.stargate.rfis.model.Link
import com.afresearchlab.stargate.rfis.model.Rfi
import com.afresearchlab.stargate.rfis.model.Sensor
import com.afresearchlab.stargate.rfis.model.Target
import com.afresearchlab.stargate.rfis.requests.CreateRfiRequest
import spock.lang.Specification
import spock.lang.Unroll

class RfiServiceSpec extends Specification {

    def rfiRepository
    def activityRepository
    def targetRepository
    def sensorRepository
    def rfiService
    def linkService
    def listsService
    def historyRepository

    def testRfi = new Rfi(null, 'test', 'US', '1.0, 2.0', 'thecity', 'theprovince', 'nai', 'IMMEDIATE', 'TO_DO', "descriptive things", "objective things", 1, 1, 1, 'custom classification', 1, 1, 'signature', 1, 1, 1, 'operation', '12 Aug 2019 13:00', '15 Nov 2019 13:00', 'collection term1', 'collection type1', 1, 'assignee1', 'collection guidance1', ['eei1', 'eei2'], null, null, null, null, null, '2017-12-01T23:43:12.123Z')
    def testCreatedRfi = new Rfi(1, 'test', 'US', '1.0, 2.0', 'thecity', 'theprovince', 'nai', 'IMMEDIATE', 'TO_DO', "descriptive things", "objective things", 1, 1, 1, 'custom classification', 1, 1, 'signature', 1, 1, 1, 'operation', '12 Aug 2019 13:00', '15 Nov 2019 13:00', 'collection term1', 'collection type1', 1, 'assignee1', 'collection guidance1', ['eei1', 'eei2'], null, null, null, null, null, '2017-12-01T23:43:12.123Z')
    def testHydratedRfi = new Rfi(1, 'test', 'US', '1.0, 2.0', 'thecity', 'theprovince', 'nai', 'IMMEDIATE', 'TO_DO', "descriptive things", "objective things", 1, 1, 1, 'custom classification', 1, 1, 'signature', 1, 1, 1, 'operation', '12 Aug 2019 13:00', '15 Nov 2019 13:00', 'collection term1', 'collection type1', 1, 'assignee1', 'collection guidance1', ['eei1', 'eei2'], [], [new Activity(1, "1", 'this is a comment', 'user1', '1538500400694', [])], [new Target(1, 1, 'target name', 'Point', 1.0, 'km', '1.0 2.0, 3.0 4.0')], [new Sensor(1, 1, 'sensor', 'sensor type', 'mode', 1, 2)], [new RecordHistory('', 'Insert', '2017-12-01T23:43:12.123Z')], '2017-12-01T23:43:12.123Z')
    def testRfiCreateRequest = new CreateRfiRequest('test', 'US', '1.0, 2.0', 'thecity', 'theprovince', 'nai', 'IMMEDIATE', 'TO_DO', "descriptive things", "objective things", 1, 1, 1, 'custom classification', 1, 1, 'signature', 1, 1, 1, 'operation', '12 Aug 2019 13:00', '15 Nov 2019 13:00', 'collection term1', 'collection type1', 1, 'assignee1', 'collection guidance1', ['eei1', 'eei2'], [new Activity(1, "1", 'this is a comment', 'user1', '1538500400694', [])], [new Target(1, 1, 'target name', 'Point', 1.0, 'km', '1.0 2.0, 3.0 4.0')], [new Sensor(1, 1, 'sensor', 'sensor type', 'mode', 1, 2)])

    def setup() {
        rfiRepository = Mock(RfiRepository)
        activityRepository = Mock(ActivityRepository)
        targetRepository = Mock(TargetRepository)
        sensorRepository = Mock(SensorRepository)
        linkService = Mock(LinkService)
        listsService = Mock(ListsService)
        historyRepository = Mock(HistoryRepository)
        rfiService = new RfiService(rfiRepository, activityRepository, targetRepository, sensorRepository, historyRepository, linkService, listsService)
    }

    def 'createRfi creates a new rfi'() {
        given:
        def newRfiArgument = new Rfi(null, 'test', 'US', '1.0, 2.0', 'thecity', 'theprovince', 'nai', 'IMMEDIATE', 'TO_DO', "descriptive things", "objective things", 1, 1, 1, 'custom classification', 1, 1, 'signature', 1, 1, 1, 'operation', '12 Aug 2019 13:00', '15 Nov 2019 13:00', 'collection term1', 'collection type1', 1, 'assignee1', 'collection guidance1', ['eei1', 'eei2'], null, null, null, null, null, null)

        when:
        def actualRfi = rfiService.create('user1', testRfiCreateRequest)

        then:
        1 * rfiRepository.save(newRfiArgument) >> testCreatedRfi
        1 * activityRepository.getAllByRfiId("1") >> [new Activity(1, "1", 'this is a comment', 'user1', '1538500400694', [])]
        1 * targetRepository.saveAll(testHydratedRfi.targets)
        1 * sensorRepository.saveAll(testHydratedRfi.sensors)
        1 * rfiRepository.get(1) >> testCreatedRfi
        1 * linkService.getAllByRecordId('1') >> []
        1 * targetRepository.getAll(1) >> [new Target(1, 1, 'target name', 'Point', 1.0, 'km', '1.0 2.0, 3.0 4.0')]
        1 * sensorRepository.getAll(1) >> [new Sensor(1, 1, 'sensor', 'sensor type', 'mode', 1, 2)]
        1 * historyRepository.save('user1', 'Insert', testCreatedRfi.id)
        1 * historyRepository.getAllByRfiId(1) >> [new RecordHistory('', 'Insert', '2017-12-01T23:43:12.123Z')]

        actualRfi == testHydratedRfi
    }

    def 'updateRfi updates an existing rfi'() {
        when:
        def updatedRfi = rfiService.updateRfi('user1', testCreatedRfi)

        then:
        1 * rfiRepository.update(testCreatedRfi)
        1 * linkService.getAllByRecordId('1') >> []
        1 * targetRepository.deleteAllByRfiId(1)
        1 * sensorRepository.deleteAllByRfiId(1)
        1 * targetRepository.saveAll(testCreatedRfi.targets)
        1 * sensorRepository.saveAll(testCreatedRfi.sensors)
        1 * historyRepository.save('user1', 'Update', testCreatedRfi.id)
        1 * rfiRepository.get(testCreatedRfi.id) >> testCreatedRfi

        updatedRfi == testCreatedRfi
    }

    @Unroll
    def 'getAll returns rfi list items with correct plottedCoordinates'() {
        given:
        def target = new Target(1, 1, 'Target1', 'test', null, null, targetCoordinates)
        def sensor = new Sensor(1, 1, 'sensor', 'type', 'mode', 1, 2)
        def rfi = new Rfi(1, 'other', 'place', rfiCoordinates, 'theothercity', 'theotherprovince', 'theothernai', 'IMMEDIATE', 'TO_DO', "descriptive things", "objective things", 2, 2, 2, 'custom classification 2', 1, 1, 'signature 2', 2, 2, 2, 'operation 2', '12 Aug 2019 13:00', '15 Nov 2019 13:00', 'collection term2', 'collection type2', 1, 'assignee2', 'collection guidance2', ['eei1', 'eei2'], null, null, null, null, null, '2017-12-01T23:43:12.123Z')

        when:
        def result = rfiService.getAll()

        then:
        1 * targetRepository.getAll(1) >> [target]
        1 * sensorRepository.getAll(1) >> [sensor]
        1 * rfiRepository.getAll() >> [rfi]
        result == [
                rfi.toBuilder().coordinates(resultCoordinates).targets([target]).sensors([sensor]).build()
        ]

        where:
        rfiCoordinates | targetCoordinates     | resultCoordinates
        '5.0, 6.0'     | 'foo bar; fizz buzz ' | 'foo bar'
        '5.0, 6.0'     | null                  | '5.0, 6.0'
        null           | null                  | null
        '5.0, 6.0'     | ''                    | '5.0, 6.0'
        null           | '10 10, 11 11, 12 12' | '10 10, 11 11, 12 12' //TODO this is not the behavior we want
    }

    def 'getAll sets targets and sensors to empty array if none are found'() {
        given:
        def rfis = new Rfi(1, 'other', 'place', '', 'theothercity', 'theotherprovince', 'theothernai', 'IMMEDIATE', 'TO_DO', "descriptive things", "objective things", 2, 2, 2, 'custom classification 2', 1, 1, 'signature 2', 2, 2, 2, 'operation 2', '12 Aug 2019 13:00', '15 Nov 2019 13:00', 'collection term2', 'collection type2', 1, 'assignee2', 'collection guidance2', ['eei1', 'eei2'], null, null, null, null, null, '2017-12-01T23:43:12.123Z')
        when:
        def result = rfiService.getAll()

        then:
        1 * targetRepository.getAll(1) >> []
        1 * sensorRepository.getAll(1) >> []
        1 * rfiRepository.getAll() >> [rfis]
        result == [rfis]
    }

    def 'get returns the rfi of the given id'() {
        when:
        def result = rfiService.get(1)
        testRfi.id = 1
        testHydratedRfi.links = [
                new Link('1', '2', 'IMM', 'IMM', 'I am linked', null),
                new Link('1', '3', 'IMM', 'PRISM', 'nomTitle', 'active'),
                new Link('4', '1', 'IMM', 'IMM', 'You are linked', 'WORKING')
        ]

        then:
        1 * rfiRepository.get(1) >> testRfi
        1 * linkService.getAllByRecordId('1') >> [
                new Link('1', '2', 'IMM', 'IMM', 'I am linked', null),
                new Link('1', '3', 'IMM', 'PRISM', 'nomTitle', 'active'),
                new Link('4', '1', 'IMM', 'IMM', 'You are linked', 'WORKING')]
        1 * activityRepository.getAllByRfiId("1") >> [new Activity(1, "1", 'this is a comment', 'user1', '1538500400694', [])]
        1 * targetRepository.getAll(1) >> [new Target(1, 1, 'target name', 'Point', 1.0, 'km', '1.0 2.0, 3.0 4.0')]
        1 * sensorRepository.getAll(1) >> [new Sensor(1, 1, 'sensor', 'sensor type', 'mode', 1, 2)]
        1 * historyRepository.getAllByRfiId(1) >> [new RecordHistory('', 'Insert', '2017-12-01T23:43:12.123Z')]
        result == testHydratedRfi
    }

    def 'delete removes the rfi and related objects'() {
        when:
        rfiService.delete(1)

        then:
        1 * rfiRepository.delete(1)
        1 * linkService.deleteAllByRecordId('1')
        1 * activityRepository.deleteAllByRfiId('1')
        1 * targetRepository.deleteAllByRfiId(1)
        1 * historyRepository.deleteAllByRfiId(1)
        1 * sensorRepository.deleteAllByRfiId(1)
        1 * listsService.removeRecordFromAllLists(new RecordIdentifier('0', '1'))
    }

    def 'returns failed health check when db is down'() {
        given:
        1 * rfiRepository.healthCheck() >> false

        when:
        def result = rfiService.healthCheck()

        then:
        result == new HealthCheck(false, -1)
    }

    def 'returns successful health check when db is up'() {
        given:
        1 * rfiRepository.healthCheck() >> true

        when:
        def result = rfiService.healthCheck()

        then:
        result == new HealthCheck(true, -1)
    }
}
