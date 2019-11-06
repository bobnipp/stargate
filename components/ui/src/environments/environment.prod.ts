import {WindowService} from '../app/window/window.service'
import {MapService} from '../app/services/map-service.service'

export const environment = {
    production: true,
    test: false,
    windowService: WindowService.prototype,
    mapService: MapService,
    jiraUrl: 'http://stargate-jira.cfapps.io/browse/'
}
