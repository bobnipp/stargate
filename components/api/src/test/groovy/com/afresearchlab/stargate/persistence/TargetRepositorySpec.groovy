package com.afresearchlab.stargate.persistence

import com.afresearchlab.stargate.rfis.model.Rfi
import com.afresearchlab.stargate.rfis.model.Target
import com.afresearchlab.stargate.spechelpers.IntegrationSpec
import org.springframework.beans.factory.annotation.Autowired

class TargetRepositorySpec extends IntegrationSpec {
    @Autowired
    TargetRepository subject

    @Autowired
    RfiRepository rfiRepository

    Long rfiId

    def setup() {
        rfiId = rfiRepository.save(new Rfi(null, 'test', 'US', '1.0 2.0', 'thecity', 'theprovince', 'nai', 'IMMEDIATE', 'TO_DO', "descriptive things", "objective things", 1, 1, 1, 'custom classification', 1, 1, 'signature', 1, 1, 1, 'operation', '12 Aug 2019 13:00', '15 Nov 2019 13:00', 'collection term1', 'collection type1', 1, 'assignee1', 'collection guidance1', ['eei1', 'eei2'], [], [], null, null, null, null)).id
    }

    def 'getAll() returns a list of targets for an rfi'() {
        given:
        subject.save(new Target(null, rfiId, "target A", "POINT", 13.2f, 'km', '33.928 -104.382'))
        subject.save(new Target(null, rfiId + 1, "other target", "POINT", 11.1f, "km", '21.232 -101.452'))

        when:
        def result = subject.getAll(rfiId)

        then:
        result[0].name == 'target A'
        result.size() == 1
    }

    def 'save() creates a new target'() {
        when:
        def createdTarget = subject.save(new Target(null, rfiId, "target A", "POINT", 13.2f, 'km', '33.928 -104.382'))
        def result = subject.get(createdTarget.id)

        then:
        createdTarget.id != null
        result.rfiId == rfiId
        result.name == "target A"
        result.type == "POINT"
        result.radius == 13.2f
        result.radiusUnit == "km"
        result.coordinates == '33.928 -104.382'

    }

    def 'get() returns the target for an rfi'() {
        given:
        def createdTarget = subject.save(new Target(null, rfiId, "target A", "POINT", 13.2f, 'km', '33.928 -104.382'))
        subject.save(new Target(null, rfiId + 1, "other target", "POINT", 11.1f, "km", '21.232 -101.452'))

        when:
        def result = subject.get(createdTarget.id)
        then:
        result.name == 'target A'
    }

    def 'deleteById() deletes targets with given id'() {
        given:
        subject.save(new Target(null, rfiId, "target A", "POINT", 13.2f, 'km', '33.928 -104.382'))

        when:
        subject.deleteAllByRfiId(1)
        def result = subject.getAll(rfiId)

        then:
        result == []
    }

    def 'deleteAllByRfiId() deletes targets with give imm_rfi_id'() {
        given:
        subject.save(new Target(null, rfiId, "target A", "POINT", 13.2f, 'km', '33.928 -104.382'))
        subject.save(new Target(null, rfiId, "other target", "POINT", 11.1f, "km", '21.232 -101.452'))
        subject.save(new Target(null, 1000, "other target", "POINT", 11.1f, "km", '21.232 -101.452'))

        when:
        subject.deleteAllByRfiId(rfiId)
        def result = subject.getAll(rfiId)

        then:
        result == []
    }
}
