import {MockWindowService} from '../test_helpers/mockWindowService'
import {StubMapService} from '../test_helpers/stubMapService'

export const environment = {
    production: false,
    test: true,
    windowService: MockWindowService.prototype,
    mapService: StubMapService,
    jiraUrl: 'http://stargate-jira.cfapps.io/browse/'
}
