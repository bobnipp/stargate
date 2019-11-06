package com.afresearchlab.stargate.filter

import spock.lang.Specification

class FiltersServiceSpec extends Specification {
    def repository
    def service

    def setup() {
        repository = Mock(FiltersRepository)
        service = new FiltersService(repository)
    }

    def "get filters for user returns the filters"() {
        given:
        def filters = new Filters([new FilterOption(1, 'val', 'sys')], [], [], [], [], '')

        1 * repository.getFiltersByUser('stargate') >> filters

        when:
        def result = service.getFiltersByUser('stargate')

        then:
        result == filters
    }

    def "save filters for user saves the filters"() {
        given:
        def filters = new Filters([new FilterOption(1, 'val', 'sys')], [], [], [], [], 'something')

        when:
        service.saveFiltersByUser('stargate', filters)

        then:
        1 * repository.saveFiltersByUser('stargate', filters)
    }
}
