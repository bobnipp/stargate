package com.afresearchlab.prismadapter.transformer

import com.afresearchlab.prismadapter.model.PrismUiNomination
import com.afresearchlab.prismadapter.transformer.PrismUiNominationTransformer
import spock.lang.Specification

class PrismUiNominationTransformerTest extends Specification {

    def 'transforms Prism response body to list of PrismUiNomination objects'() {
        given:
        def prismResponseBody = '{"data": [{"disabled": false, "savable": true, "leaf": true, "editable": true, "expanded": false, "iconCls": "prism-nom-icon", "clickable": true, "selfLoad": true, "data": {"recordId": "000000000001303PR7AWS", "recordName": "1000", "prismState": "A", "type": "NOM", "prismStatus": ""}, "text": "1000(1000)", "id": "0-000000000001303PR7AWS"}, {"disabled": false, "savable": true, "leaf": true, "editable": true, "expanded": false, "iconCls": "prism-nom-icon", "clickable": true, "selfLoad": true, "data": {"recordId": "000000000001302PR7AWS", "recordName": "1234", "prismState": "A", "type": "NOM", "prismStatus": ""}, "text": "1234(1234)", "id": "0-000000000001302PR7AWS"}], "success": true}'
        def result = PrismUiNominationTransformer.transform(prismResponseBody)

        expect:
        result == [
            PrismUiNomination.builder().key('000000000001303PR7AWS').name('1000').build(),
            PrismUiNomination.builder().key('000000000001302PR7AWS').name('1234').build()
        ]
    }
}
