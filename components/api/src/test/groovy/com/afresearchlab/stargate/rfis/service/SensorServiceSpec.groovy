package com.afresearchlab.stargate.rfis.service

import com.afresearchlab.stargate.persistence.SensorRepository
import spock.lang.Specification

class SensorServiceSpec extends Specification {
    def sensorRepository
    def sensorService

    def setup() {
        sensorRepository = Mock(SensorRepository)
        sensorService = new SensorService(sensorRepository)
    }

    def 'deletes a sensor'() {
        when:
        sensorService.deleteById(1)

        then:
        1 * sensorRepository.deleteById(1)
    }
}
