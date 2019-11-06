package com.afresearchlab.stargate.rfis.controller

import com.afresearchlab.stargate.rfis.service.RfiService
import com.afresearchlab.stargate.spechelpers.ValidationSpec
import com.google.gson.reflect.TypeToken
import org.junit.Ignore
import spock.lang.Unroll

import static com.afresearchlab.stargate.spechelpers.ApiHelpers.assertSuccess
import static com.afresearchlab.stargate.spechelpers.ApiHelpers.baseRequest

@Ignore
class SearchControllerSpec extends ValidationSpec {

    def RFI_SEARCH_RESULT_LIST_TYPE = new TypeToken<List<String>>() {}.getType()

    @Unroll
    def 'search returns the search results from the service'() {
        given:
        def searchResults = ['foo', 'bar']
        def service = Mock(RfiService)
        def controller = new SearchController(service)
        1 * service.search(title, country) >> searchResults

        expect:
        def result = baseRequest(controller)
            .when()
            .get("/api/v1/search/rfis?${queryString}")
        assertSuccess(result)

        deserializeWithValidation(result, RFI_SEARCH_RESULT_LIST_TYPE, 'search/search-rfis.response') == searchResults

        where:
        title | country | queryString
        'foo' | null    | 'title=foo'
        null  | 'foo'   | 'country=foo'
        'foo' | 'bar'   | 'title=foo&country=bar'
    }
}
