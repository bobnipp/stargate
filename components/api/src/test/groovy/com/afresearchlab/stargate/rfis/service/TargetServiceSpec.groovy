package com.afresearchlab.stargate.rfis.service;

import com.afresearchlab.stargate.persistence.TargetRepository;
import spock.lang.Specification;

class TargetServiceSpec extends Specification {
    def targetRepository
    def targetService

    def setup() {
        targetRepository = Mock(TargetRepository)
        targetService = new TargetService(targetRepository)
    }

    def 'deletes a target'() {
        when:
        targetService.deleteById(1)

        then:
        1 * targetRepository.deleteById(1)
    }
}
