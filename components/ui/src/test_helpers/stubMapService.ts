import {Injectable} from '@angular/core'
import {Observable} from 'rxjs/internal/Observable'

@Injectable()
export class StubMapService {
    private stubMap = new StubMap()

    ready(onReady) {
        onReady(new StubMoW(this.stubMap))
    }

    createMap() { return this.stubMap }

    createIMMOverlay(mow: any): any {
        return {overlay: 'object'}
    }
}

class StubMap {
    ready(onReady) {
        onReady()
    }

    setCenter() {}

    addOverlay() {}

    addFeatures() {}

    clearFeatureLayer() {}

    setBasemap() {}

    events = Observable.create()
}

class StubFeatureFactory {
    createFeatureOverlay() {}

    createPointFeature() {}

    createPolygonFeature() {}

    createCircleFeature() {}
}

class StubMoW {
    private stubFeatureFactory = new StubFeatureFactory()

    constructor(private stubMap: StubMap) {}

    ready(onReady) {onReady()}

    Map() { return this.stubMap }

    FeatureFactory = this.stubFeatureFactory
    Basemap = {ID: {CANVAS_SLATE: 'slate'}}
    Events = {ID: {FEATURE_SELECT: {}, MAP_CLICK: {}}}
}

