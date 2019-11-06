package com.afresearchlab.stargate.persistence

import com.afresearchlab.stargate.rfis.model.Rfi
import com.afresearchlab.stargate.rfis.model.Sensor
import com.afresearchlab.stargate.spechelpers.IntegrationSpec
import org.springframework.beans.factory.annotation.Autowired

class SensorRepositorySpec extends IntegrationSpec {
    @Autowired
    SensorRepository subject

    @Autowired
    RfiRepository rfiRepository

    Long rfiId

    def setup() {
        rfiId = rfiRepository.save(new Rfi(null, 'test', 'US', '1.0 2.0', 'thecity', 'theprovince', 'nai', 'IMMEDIATE', 'TO_DO', "descriptive things", "objective things", 1, 1, 1, 'custom classification', 1, 1, 'signature', 1, 1, 1, 'operation', '12 Aug 2019 13:00', '15 Nov 2019 13:00', 'collection term1', 'collection type1', 1, 'assignee1', 'collection guidance1', ['eei1', 'eei2'], [], [], null, null, null, null)).id
    }

    def 'getAll() returns a list of sensors for an rfi'() {
        given:
        subject.save(new Sensor(null, rfiId, 'sensor 1', 'sensor type 1', 'mode 1', 1, 2))
        subject.save(new Sensor(null, rfiId + 1, 'sensor 2', 'sensor type 2', 'mode 2', 5, 10))

        when:
        def result = subject.getAll(rfiId)

        then:
        result[0].sensor == 'sensor 1'
        result.size() == 1
    }

    def 'save() creates a new sensor'() {
        when:
        def createdSensor = subject.save(new Sensor(null, rfiId, 'sensor 1', 'sensor type 1', 'mode 1', 1, 2))
        def result = subject.get(createdSensor.id)

        then:
        createdSensor.id != null
        result.rfiId == rfiId
        result.sensor == 'sensor 1'
        result.sensorType == 'sensor type 1'
        result.mode == 'mode 1'
        result.desiredQuality == 1
        result.requiredQuality == 2

    }

    def 'get() returns the sensor'() {
        given:
        def createdSensor = subject.save(new Sensor(null, rfiId, 'sensor 1', 'sensor type 1', 'mode 1', 1, 2))
        subject.save(new Sensor(null, rfiId + 1, 'other sensor', 'sensor type 2', 'mode 2', 1, 2))

        when:
        def result = subject.get(createdSensor.id)
        then:
        result.sensor == 'sensor 1'
    }

    def 'deleteById() deletes the sensor with given id'() {
        given:
        def createdSensor = subject.save(new Sensor(null, rfiId, 'sensor 1', 'sensor type 1', 'mode 1', 1, 2))

        when:
        subject.deleteById(createdSensor.id)
        def result = subject.getAll(rfiId)

        then:
        result == []
    }

    def 'deleteAllByRfiId() deletes sensors with give imm_rfi_id'() {
        given:
        subject.save(new Sensor(null, rfiId, 'sensor 1', 'sensor type 1', 'mode 1', 1, 2))
        subject.save(new Sensor(null, rfiId, 'sensor 2', 'sensor type 2', 'mode 2', 5, 10))
        subject.save(new Sensor(null, 1000, 'sensor 3', 'sensor type 3', 'mode 3', 5, 10))

        when:
        subject.deleteAllByRfiId(rfiId)
        def result = subject.getAll(rfiId)
        def result2 = subject.getAll(1000)

        then:
        result == []
        result2.size() == 1
    }
}
