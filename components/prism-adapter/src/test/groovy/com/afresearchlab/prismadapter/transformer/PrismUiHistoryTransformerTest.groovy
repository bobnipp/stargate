package com.afresearchlab.prismadapter.transformer

import com.afresearchlab.prismadaptermodels.RecordHistory
import spock.lang.Specification

class PrismUiHistoryTransformerTest extends Specification {
    def "transforms json into a history list"() {
        given:
        def historyJson = '{"data":[{"action":"Update","modifiedOn":"11/01/2018 11:46:53","modifiedBy":"CFREKING (cfreking)"},{"action":"Update","modifiedOn":"11/01/2018 11:46:13","modifiedBy":"JGUSTINE (jgustine)"},{"action":"Insert","modifiedOn":"11/01/2018 11:45:30","modifiedBy":"JROSSMEISL (jrossmeisl)"}],"success":true}'
        def result = PrismUiHistoryTransformer.transform(historyJson)

        expect:
        result == [
                RecordHistory.builder().username('cfreking').date('11/01/2018 11:46:53').action('Update').build(),
                RecordHistory.builder().username('jgustine').date('11/01/2018 11:46:13').action('Update').build(),
                RecordHistory.builder().username('jrossmeisl').date('11/01/2018 11:45:30').action('Insert').build()
        ]
    }
}
