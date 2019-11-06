package com.afresearchlab.stargate.rfis.service

import com.afresearchlab.stargate.persistence.ActivityRepository
import com.afresearchlab.stargate.rfis.model.Activity
import com.afresearchlab.stargate.rfis.model.ActivityRequest
import com.afresearchlab.stargate.rfis.model.Attachment
import com.afresearchlab.stargate.rfis.model.AttachmentRequest
import com.afresearchlab.stargate.storage.StorageService
import spock.lang.Specification

class ActivityServiceSpec extends Specification {
    def activityService
    def storageService
    def activityRepository

    def setup() {
        activityRepository = Mock(ActivityRepository)
        storageService = Mock(StorageService)
        activityService = new ActivityService(activityRepository, storageService)
    }

    def 'create creates a new activity'() {
        given:
        def currentDate = new Date().toString()
        def activityRequest = new ActivityRequest(null, '1', 'some activity text', 'someuser', currentDate, [new AttachmentRequest('test.txt', 'bytePackage', 'size')])
        def activity = new Activity(null, '1', 'some activity text', 'someuser', currentDate, [new Attachment('test.txt', 'size', 'txt')])

        when:
        activityService.create(activityRequest)

        then:
        1 * storageService.upload(_, '1')
        1 * activityRepository.save(activity)
    }

    def 'returns all by rfiId'() {
        given:
        def activities = [
            new Activity(1, '1', 'text', 'username', 'date', []),
            new Activity(2, '1', 'text2', 'username1', 'date1', [])
        ]

        when:
        def result = activityService.getAllByRfiId('1')

        then:
        1 * activityRepository.getAllByRfiId('1') >> activities

        result == activities
    }

    def 'deletes an activity'() {
        given:
        1 * activityRepository.get(1) >> new Activity(1, '1', 'text', 'username', 'date', [new Attachment('test.txt', '11 kB', 'txt')])

        when:
        activityService.deleteById(1)

        then:
        1 * activityRepository.deleteById(1)
        1 * storageService.delete('1', 'test.txt')
    }
}
