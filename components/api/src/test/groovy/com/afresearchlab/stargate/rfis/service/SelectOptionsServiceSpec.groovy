package com.afresearchlab.stargate.rfis.service

import com.afresearchlab.stargate.persistence.SelectOptionsRepository
import com.afresearchlab.stargate.rfis.model.SelectOption
import spock.lang.Specification

class SelectOptionsServiceSpec extends Specification {

    def repository
    def service

    def setup() {
        repository = Mock(SelectOptionsRepository)
        service = new SelectOptionsService(repository)
    }

    def 'getRfiOptions retrieves all options for RFIs'() {
        given:
        def expectedOptions = [
            "foo": [new SelectOption(1, "Bar"), new SelectOption(2, "Baz")],
            "bar": [new SelectOption(1, "Quo"), new SelectOption(2, "Qux")]
        ]

        1 * repository.getOptions(SelectOptionsRepository.SelectOptionsObjectType.RFI) >> expectedOptions

        when:
        def result = service.getRfiOptions()

        then:
        result == expectedOptions
    }

    def 'getNomOptions retrieves all options for NOMs'() {
        given:
        def expectedOptions = [
            "foo": [new SelectOption(1, "Bar"), new SelectOption(2, "Baz")],
            "bar": [new SelectOption(1, "Quo"), new SelectOption(2, "Qux")]
        ]

        1 * repository.getOptions(SelectOptionsRepository.SelectOptionsObjectType.NOM) >> expectedOptions

        when:
        def result = service.getNomOptions()

        then:
        result == expectedOptions
    }
}
