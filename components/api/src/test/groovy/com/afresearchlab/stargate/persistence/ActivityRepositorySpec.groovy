package com.afresearchlab.stargate.persistence

import com.afresearchlab.stargate.rfis.model.Activity
import com.afresearchlab.stargate.rfis.model.Rfi
import com.afresearchlab.stargate.spechelpers.IntegrationSpec
import org.springframework.beans.factory.annotation.Autowired

class ActivityRepositorySpec extends IntegrationSpec {
    @Autowired
    ActivityRepository subject

    @Autowired
    RfiRepository rfiRepository

    Activity testActivity
    Activity testActivity2
    Long rfiId

    def setup() {
        rfiId = rfiRepository.save(new Rfi(null, 'test', 'US', '1.0 2.0', 'thecity', 'theprovince', 'nai', 'IMMEDIATE', 'TO_DO', "descriptive things", "objective things", 1, 1, 1, 'custom classification', 1, 1, 'signature', 1, 1, 1, 'operation', '12 Aug 2019 13:00', '15 Nov 2019 13:00', 'collection term1', 'collection type1', 1, 'assignee1', 'collection guidance1', ['eei1', 'eei2'], [], [], null, null, null, null)).id
        testActivity = new Activity(null, rfiId.toString(), 'No comment', 'user1', Long.toString(new Date().getTime()), [])
        testActivity2 = new Activity(null, rfiId.toString(), 'A comment', 'user2', Long.toString(new Date().getTime()), [])
    }

    def 'it fetches all activities for an rfi in descending date order'() {
        given:
        def rfiId2 = rfiRepository.save(new Rfi(null, 'test', 'US', '1.0 2.0', 'thecity', 'theprovince', 'nai', 'IMMEDIATE', 'TO_DO', "descriptive things", "objective things", 1, 1, 1, 'custom classification', 1, 1, 'signature', 1, 1, 1, 'operation', '12 Aug 2019 13:00', '15 Nov 2019 13:00', 'collection term1', 'collection type1', 1, 'assignee1', 'collection guidance1', ['eei1', 'eei2'], [], [], null, null, null, null)).id
        def testActivity3 = new Activity(null, rfiId2.toString(), 'Some comment', 'user3', Long.toString(new Date().getTime()), [])
        def activities = [
            testActivity, testActivity2, testActivity3
        ]
        activities.each {
            subject.save(it)
        }

        when:
        def result = subject.getAllByRfiId(rfiId.toString())

        then:
        result[0].timestamp == activities[1].timestamp
        result[1].timestamp == activities[0].timestamp
    }


    def 'save creates a new activity'() {
        when:
        def createdActivity = subject.save(testActivity)
        def result = subject.getAllByRfiId(createdActivity.rfiId.toString())[0]

        then:
        createdActivity.id != null
        result.rfiId == rfiId.toString()
        result.text == 'No comment'
        result.username == 'user1'
        result.timestamp != null
    }

    def 'deleteById() deletes activity with given id'() {
        given:
        def createdActivity = subject.save(testActivity)

        when:
        subject.deleteById(createdActivity.id)
        def result = subject.getAllByRfiId(rfiId.toString())

        then:
        result == []
    }
}
