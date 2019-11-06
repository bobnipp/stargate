// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

import {WindowService} from '../app/window/window.service'
import {MapService} from '../app/services/map-service.service'

export const environment = {
    production: false,
    test: false,
    windowService: WindowService.prototype,
    mapService: MapService,
    jiraUrl: 'http://stargate-jira.cfapps.io/browse/'
}
