package com.afresearchlab.stargate.filter

import com.afresearchlab.stargate.spechelpers.IntegrationSpec
import org.springframework.beans.factory.annotation.Autowired

class FiltersRepositorySpec extends IntegrationSpec {

    @Autowired
    FiltersRepository subject

    def filters = new Filters([new FilterOption(1, 'val', 'sys')], [], [], [], [], '')
    def filters1 = new Filters([new FilterOption(1, 'val', 'sys')], [], [], [], [new FilterOption(1, 'val', 'sys')], 'something')

    def "get filters for user returns the filters"() {
        given:
        subject.saveFiltersByUser('stargate', filters)
        subject.saveFiltersByUser('stargate_1', filters1)

        when:
        def result = subject.getFiltersByUser('stargate')

        then:
        result == filters
    }

    def "save filters for user only saves one row for the user"() {
        given:
        subject.saveFiltersByUser('stargate', filters)
        subject.saveFiltersByUser('stargate', filters1)

        when:
        def result = subject.getFiltersByUser('stargate')

        then:
        result == filters1
    }

}
