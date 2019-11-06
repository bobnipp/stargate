import {async, TestBed} from '@angular/core/testing'

import {MapComponent} from './map.component'
import {MapConfigService} from '../services/map-config.service'
import {AgmCoreModule, LAZY_MAPS_API_CONFIG} from '@agm/core'
import {makeNomCrListItem, makeRfiListItem} from '../../test_helpers/utilities'
import {MockHttpClient} from '../../test_helpers/mockHttpClient'
import {HttpClient} from '@angular/common/http'
import {Store, StoreModule} from '@ngrx/store'
import {MockPrismService} from '../../test_helpers/mockPrismService'
import {PrismService} from '../services/prism.service'

import {AppState} from '../state/appstate'
import {prismNominationsReducer, rfiListReducer} from '../state/reducers'
import {RfiService} from '../services/rfi.service'
import {MockRfiService} from '../../test_helpers/mockRfiService'
import {AgmSnazzyInfoWindowModule} from '@agm/snazzy-info-window'
import {CoordinatesService} from '../services/coordinates.service'
import {SidebarMapListComponent} from './sidebar-map-list/sidebar-map-list.component'
import {MockWindowService} from '../../test_helpers/mockWindowService'
import {WindowService} from '../window/window.service'
import {MapService} from '../services/map-service.service'
import {Observable} from 'rxjs/internal/Observable'
import Spy = jasmine.Spy
import SpyObj = jasmine.SpyObj
import {MapRecordItemViewComponent} from '../list/record-item-view/map-record-item-view.component'
import {CopyButtonComponent} from "../common/copy-button/copy-button.component";

describe('MapComponent', () => {
    let component: MapComponent
    let store: Store<AppState>
    let fetchRfisSpy: Spy
    let fetchRfiSpy: Spy
    let mockCoordinatesService: SpyObj<CoordinatesService>
    let mockMapService: SpyObj<MapService>
    let mockMap
    let mockFeatureFactory
    let mockMoW

    beforeEach(async(() => {
        mockMap = {
            ready: function (callback) {callback()},
            setCenter: jasmine.createSpy('setCenter'),
            addOverlay: jasmine.createSpy('addOverlay'),
            addFeatures: jasmine.createSpy('addFeatures'),
            clearFeatureLayer: jasmine.createSpy('clearFeatureLayer'),
            setBasemap: jasmine.createSpy('setBasemap'),
            performIdentify: jasmine.createSpy('performIdentify'),
            events: Observable.create()
        }

        mockFeatureFactory = {
            createFeatureOverlay: jasmine.createSpy('createFeatureOverlay').and.returnValue({id: 2}),
            createPointFeature: jasmine.createSpy('createPointFeature'),
            createPolygonFeature: jasmine.createSpy('createPolygonFeature'),
            createCircleFeature: jasmine.createSpy('createCircleFeature'),
            createLineFeature: jasmine.createSpy('createLineFeature')
        }

        mockMoW = {
            ready(callback: Function) { callback() },
            Map: mockMap,
            FeatureFactory: mockFeatureFactory,
            Basemap: {ID: {CANVAS_SLATE: 'slate'}},
            Events: {ID: {FEATURE_SELECT: {}, MAP_CLICK: {}}}
        }

        const mockHttpClient = new MockHttpClient()
        const mockPrismService = new MockPrismService()
        const mockRfiService = new MockRfiService()
        const mockWindowService = new MockWindowService()

        mockCoordinatesService = jasmine.createSpyObj<CoordinatesService>('coordinatesService', ['getLatLng'])
        mockMapService = jasmine.createSpyObj<MapService>('mapService', ['ready', 'createMap', 'createIMMOverlay'])
        mockMapService.ready.and.callFake((onReady) => onReady(mockMoW))
        mockMapService.createMap.and.returnValue(mockMap)
        mockMapService.createIMMOverlay.and.returnValue( {overlay: 'object'})

        fetchRfisSpy = spyOn(mockRfiService, 'fetchRfis')
        fetchRfiSpy = spyOn(mockRfiService, 'fetchRfi')

        TestBed.configureTestingModule({
            declarations: [MapComponent, SidebarMapListComponent, MapRecordItemViewComponent, CopyButtonComponent],
            providers: [
                {provide: WindowService, useValue: mockWindowService},
                {provide: HttpClient, useValue: mockHttpClient},
                {provide: PrismService, useValue: mockPrismService},
                {provide: RfiService, useValue: mockRfiService},
                {provide: CoordinatesService, useValue: mockCoordinatesService},
                {provide: MapService, useValue: mockMapService},
                {provide: LAZY_MAPS_API_CONFIG, useClass: MapConfigService}
            ],
            imports: [AgmCoreModule.forRoot(),
                AgmSnazzyInfoWindowModule,
                StoreModule.forRoot({
                    rfis: rfiListReducer,
                    prismNominations: prismNominationsReducer
                })
            ]
        })

        component = TestBed.createComponent(MapComponent).componentInstance
        store = TestBed.get(Store)

        component.MoW = mockMoW
        component.map = mockMap
        component.featureOverlay = {overlay: 'object'}
    }))

    describe('ngOnInit', () => {
        it('toggles loading', () => {
            component.activeTab = 'MAP'

            expect(component.loading).toEqual(true)
            component.ngOnInit()
            expect(component.loading).toEqual(false)
        })
    })

    describe('map view constraints', () => {
        it('calls the map perform identify method if constraints are turned on', () => {
            component.doConstrainResults(false)
            expect(mockMap.performIdentify).not.toHaveBeenCalled()

            component.doConstrainResults(true)
            expect(mockMap.performIdentify).toHaveBeenCalled()
        })
    })

    describe('initializeMap', () => {
        it('sets the center and feature overlay', () => {
            const setCenterSpy = component.map.setCenter
            const addOverlaySpy = component.map.addOverlay

            component.initializeMap()

            expect(setCenterSpy).toHaveBeenCalledWith([component.centerLat, component.centerLng], 0)
            expect(addOverlaySpy).toHaveBeenCalledWith({overlay: 'object'})
            expect(component.featureOverlay).toEqual({overlay: 'object'})
        })
    })

    describe('renderRfiCoordinates', () => {
        let rfiPointWithTargets = makeRfiListItem({
            id: 'RFI-POINT',
            title: 'bar',
            coordinates: '1 2',
            justification: 'I said so',
            status: 'TO_DO',
            priority: 'IMMEDIATE',
            targets: [{
                rfiId: 'FOO-2',
                type: 'POINT',
                coordinates: '32.2345n 141.2345',
                radiusUnit: 'NM',
                name: 'target 1'
            }, {
                rfiId: 'FOO-3',
                type: 'POINT',
                coordinates: '-32.2345 -141.2345',
                radiusUnit: 'NM',
                name: 'target 2'
            }, {
                rfiId: 'FOO-4',
                type: 'POINT',
                coordinates: '22.2345n 41.2345',
                radiusUnit: 'NM',
                radius: 10,
                name: 'target 3'
            }]
        })
        let rfiBasicPoint = makeRfiListItem({
            id: 'buzz',
            title: 'zee',
            coordinates: '32 141',
            justification: 'no target',
            status: 'TO_DO',
            priority: 'IMMEDIATE',
            targets: []
        })
        let rfiPolygon = makeRfiListItem({
            id: 'buzz',
            title: 'zee',
            coordinates: '32 141',
            justification: 'no target',
            status: 'TO_DO',
            priority: 'IMMEDIATE',
            targets: [{
                rfiId: 'FOO-2',
                type: 'DSA',
                coordinates: '10 100; -10 110; 10 120',
                radiusUnit: 'NM',
                name: 'target 1'
            }]
        })
        let rfiLine = makeRfiListItem({
            id: 'buzz',
            title: 'zee',
            coordinates: '32 141',
            justification: 'no target',
            status: 'TO_DO',
            priority: 'IMMEDIATE',
            targets: [{
                rfiId: 'FOO-2',
                type: 'LOC',
                coordinates: '10 100; -10 110',
                radiusUnit: 'NM',
                name: 'target 1'
            }]
        })
        let rfiNoCoordinates = makeRfiListItem({
            id: 'buzz',
            title: 'zee',
            coordinates: '',
            justification: 'no target',
            status: 'TO_DO',
            priority: 'IMMEDIATE',
            targets: []
        })

        let createFeatureSpy
        let addFeaturesSpy
        let createCircleFeatureSpy
        let createPolygonFeatureSpy
        let createLineFeatureSpy

        beforeEach(() => {
            createFeatureSpy = mockMoW.FeatureFactory.createPointFeature.and.returnValues({feature: 1})
            addFeaturesSpy = component.map.addFeatures
            createCircleFeatureSpy = mockMoW.FeatureFactory.createCircleFeature
            createPolygonFeatureSpy = mockMoW.FeatureFactory.createPolygonFeature
            createLineFeatureSpy = mockMoW.FeatureFactory.createLineFeature

            component.featureOverlay = {id: 2}
        })

        describe('point', () => {
            it('draws points from targets', () => {
                mockCoordinatesService.getLatLng.and.returnValues([32.2345, 141.2345], [-32.2345, -141.2345], [22.2345, 41.2345])

                component.filteredRfis = [rfiPointWithTargets]

                const point1 = {
                    point: [32.2345, 141.2345],
                    attributes: {
                        title: 'bar',
                        targetName: 'target 1',
                        justification: 'I said so',
                        status: 'TO_DO',
                        system: 'IMM',
                        id: 'RFI-POINT',
                        idKey: 'ID',
                        coordinates: '32.2345 141.2345'
                    },
                    style: {
                        pointRadius: 8,
                        fillColor: '#d79d3d',
                        strokeColor: '#FFFFFF',
                        strokeWidth: 2,
                        fillOpacity: 0.8
                    }
                }
                const point2 = {
                    point: [-32.2345, -141.2345],
                    attributes: {
                        title: 'bar',
                        targetName: 'target 2',
                        justification: 'I said so',
                        status: 'TO_DO',
                        system: 'IMM',
                        id: 'RFI-POINT',
                        idKey: 'ID',
                        coordinates: '-32.2345 -141.2345'
                    },
                    style: {
                        pointRadius: 8,
                        fillColor: '#d79d3d',
                        strokeColor: '#FFFFFF',
                        strokeWidth: 2,
                        fillOpacity: 0.8
                    }
                }
                const circle = {
                    attributes: {
                        title: 'bar',
                        targetName: 'target 3',
                        justification: 'I said so',
                        status: 'TO_DO',
                        system: 'IMM',
                        id: 'RFI-POINT',
                        idKey: 'ID',
                        coordinates: '22.2345 41.2345'
                    },
                    circle: {
                        center: {
                            lat: 22.2345,
                            lon: 41.2345
                        },
                        radius: 18.52
                    },
                    style: {
                        fillColor: '#d79d3d',
                        fillOpacity: 0.15,
                        strokeColor: '#d79d3d'
                    }
                }

                component.renderRfiCoordinates()

                expect(createFeatureSpy).toHaveBeenCalledWith(point1)
                expect(createFeatureSpy).toHaveBeenCalledWith(point2)
                expect(addFeaturesSpy).toHaveBeenCalledTimes(3)
                expect(addFeaturesSpy).toHaveBeenCalledWith(2, {feature: 1})
                expect(createCircleFeatureSpy).toHaveBeenCalledWith(circle)
            })

            it('draws a point from basic data', () => {
                mockCoordinatesService.getLatLng.and.returnValue([32, 141])

                component.filteredRfis = [rfiBasicPoint]

                const point = {
                    point: [32, 141],
                    attributes: {
                        title: 'zee',
                        targetName: '',
                        justification: 'no target',
                        status: 'TO_DO',
                        system: 'IMM',
                        id: 'buzz',
                        idKey: 'ID',
                        coordinates: '32 141'
                    },
                    style: {
                        pointRadius: 8,
                        fillColor: '#d79d3d',
                        strokeColor: '#FFFFFF',
                        strokeWidth: 2,
                        fillOpacity: 0.8
                    }
                }

                component.renderRfiCoordinates()

                expect(createFeatureSpy).toHaveBeenCalledWith(point)
                expect(addFeaturesSpy).toHaveBeenCalledTimes(1)
                expect(addFeaturesSpy).toHaveBeenCalledWith(2, {feature: 1})

                expect(createCircleFeatureSpy).not.toHaveBeenCalled()
            })

            it('does not draw a point for invalid target coordinates', () => {
                mockCoordinatesService.getLatLng.and.returnValue(null)

                component.filteredRfis = [rfiPointWithTargets]

                component.renderRfiCoordinates()

                expect(createFeatureSpy).not.toHaveBeenCalled()
                expect(addFeaturesSpy).not.toHaveBeenCalled()
                expect(createCircleFeatureSpy).not.toHaveBeenCalled()
            })

            it('does not draw a point for invalid basic coordinates', () => {
                mockCoordinatesService.getLatLng.and.returnValue(null)

                component.filteredRfis = [rfiBasicPoint]

                component.renderRfiCoordinates()

                expect(createFeatureSpy).not.toHaveBeenCalled()
                expect(addFeaturesSpy).not.toHaveBeenCalled()
                expect(createCircleFeatureSpy).not.toHaveBeenCalled()
            })

            it('does not draw a point if there are no coordinates on the rfi', () => {
                mockCoordinatesService.getLatLng.and.returnValue(null)

                component.filteredRfis = [rfiNoCoordinates]

                component.renderRfiCoordinates()

                expect(mockCoordinatesService.getLatLng).not.toHaveBeenCalled()
                expect(createFeatureSpy).not.toHaveBeenCalled()
                expect(addFeaturesSpy).not.toHaveBeenCalled()
                expect(createCircleFeatureSpy).not.toHaveBeenCalled()
            })
        })

        describe('line', () => {
            it('draws a line', () => {
                mockCoordinatesService.getLatLng.and.returnValues([10, 100], [-10, 110])

                component.filteredRfis = [rfiLine]

                const line = {
                    line: [[10, 100], [-10, 110]],
                    attributes: {
                        title: 'zee',
                        targetName: 'target 1',
                        justification: 'no target',
                        status: 'TO_DO',
                        system: 'IMM',
                        id: 'buzz',
                        idKey: 'ID',
                        coordinates: '10 100, -10 110'
                    },
                    style: {
                        fillColor: '#d79d3d',
                        fillOpacity: 0.15,
                        strokeColor: '#d79d3d',
                        strokeWidth: 2
                    }
                }

                createPolygonFeatureSpy.and.returnValues({feature: line})

                component.renderRfiCoordinates()

                expect(createLineFeatureSpy).toHaveBeenCalledWith(line)
                expect(addFeaturesSpy).toHaveBeenCalledTimes(1)

                expect(createCircleFeatureSpy).not.toHaveBeenCalled()
            })

            it('does not draw lines with invalid coordinates', () => {
                mockCoordinatesService.getLatLng.and.returnValue(null)

                component.filteredRfis = [rfiLine]

                component.renderRfiCoordinates()

                expect(createFeatureSpy).not.toHaveBeenCalled()
                expect(addFeaturesSpy).not.toHaveBeenCalled()
                expect(createPolygonFeatureSpy).not.toHaveBeenCalled()
            })
        })

        describe('polygon', () => {
            it('draws a polygon', () => {
                mockCoordinatesService.getLatLng.and.returnValues([10, 100], [-10, 110], [10, 120])

                component.filteredRfis = [rfiPolygon]

                const polygon = {
                    polygon: [[10, 100], [-10, 110], [10, 120]],
                    attributes: {
                        title: 'zee',
                        targetName: 'target 1',
                        justification: 'no target',
                        status: 'TO_DO',
                        system: 'IMM',
                        id: 'buzz',
                        idKey: 'ID',
                        coordinates: '10 100, -10 110, 10 120'
                    },
                    style: {
                        fillColor: '#d79d3d',
                        fillOpacity: 0.15,
                        strokeColor: '#d79d3d',
                        strokeWidth: 2
                    }
                }

                createPolygonFeatureSpy.and.returnValues({feature: polygon})

                component.renderRfiCoordinates()

                expect(createPolygonFeatureSpy).toHaveBeenCalledWith(polygon)
                expect(addFeaturesSpy).toHaveBeenCalledTimes(1)
                expect(addFeaturesSpy).toHaveBeenCalledWith(2, {feature: polygon})
                expect(createCircleFeatureSpy).not.toHaveBeenCalled()
            })

            it('does not draw polygons with invalid coordinates', () => {
                mockCoordinatesService.getLatLng.and.returnValue(null)

                component.filteredRfis = [rfiPolygon]

                component.renderRfiCoordinates()

                expect(createFeatureSpy).not.toHaveBeenCalled()
                expect(addFeaturesSpy).not.toHaveBeenCalled()
                expect(createPolygonFeatureSpy).not.toHaveBeenCalled()
            })
        })
    })

    describe('renderNominationCoordinates', () => {
        let nomWithAllTheTargets = makeNomCrListItem({
            id: 'id1',
            title: 'title1',
            status: 'TO_DO',
            priority: 'IMMEDIATE',
            targets: [
                {
                    coordinates: '30 -140',
                    name: 'target 1'
                },
                {
                    coordinates: '30 -140',
                    type: 'DSA',
                    radiusUnit: 'NM',
                    radius: 10,
                    name: 'target 2'
                },
                {
                    rfiId: 'FOO-2',
                    type: 'DSA',
                    coordinates: '10 100, -10 110, 10 120',
                    radiusUnit: 'NM',
                    name: 'target 3'
                }]
        })
        let nomPointWithTargetWithoutRadius = makeNomCrListItem({
            id: 'id1',
            title: 'title1',
            status: 'TO_DO',
            priority: 'IMMEDIATE',
            targets: [
                {
                    coordinates: '30 -140',
                    name: 'target 1'
                }
            ]
        })
        let nomPointWithTargetWithRadius = makeNomCrListItem({
            id: 'id1',
            title: 'title1',
            status: 'TO_DO',
            priority: 'IMMEDIATE',
            targets: [{
                coordinates: '30 -140',
                type: 'Point',
                radiusUnit: 'NM',
                radius: 10,
                name: 'target 1'
            }]
        })
        let nomPolygon = makeNomCrListItem({
            id: 'id1',
            title: 'title1',
            status: 'TO_DO',
            priority: 'IMMEDIATE',
            targets: [{
                rfiId: 'FOO-2',
                type: 'DSA',
                coordinates: '10 100, -10 110, 10 120',
                radiusUnit: 'NM',
                name: 'target 1'
            }]
        })

        let addFeaturesSpy
        let createCircleFeatureSpy
        let createPolygonFeatureSpy

        beforeEach(() => {
            component.featureOverlay = {id: 2}
            addFeaturesSpy = component.map.addFeatures
            createCircleFeatureSpy = mockMoW.FeatureFactory.createCircleFeature
            createPolygonFeatureSpy = mockMoW.FeatureFactory.createPolygonFeature
        })

        it('draws multiple targets', () => {
            mockCoordinatesService.getLatLng.and.returnValues([30, -140], [20, 120], [10, 100], [-10, 110], [10, 120])
            let pointWithoutRadius = {
                point: [30, -140],
                attributes: {
                    title: 'title1',
                    targetName: 'target 1',
                    justification: undefined,
                    status: 'TO_DO',
                    system: 'PRISM',
                    id: 'id1',
                    idKey: 'NOM ID',
                    coordinates: '30 -140'
                },
                style: {
                    pointRadius: 8,
                    fillColor: '#5b973e',
                    strokeColor: '#FFFFFF',
                    strokeWidth: 2,
                    fillOpacity: 0.8
                }
            }
            const pointRadius = {
                attributes: {
                    title: 'title1',
                    targetName: 'target 2',
                    justification: undefined,
                    status: 'TO_DO',
                    system: 'PRISM',
                    id: 'id1',
                    idKey: 'NOM ID',
                    coordinates: '20 120'
                },
                circle: {
                    center: {
                        lat: 20,
                        lon: 120
                    },
                    radius: 18.52
                },
                style: {
                    fillColor: '#5b973e',
                    fillOpacity: 0.15,
                    strokeColor: '#5b973e'
                }
            }

            const polygon = {
                polygon: [[10, 100], [-10, 110], [10, 120]],
                attributes: {
                    title: 'title1',
                    targetName: 'target 3',
                    justification: undefined,
                    status: 'TO_DO',
                    system: 'PRISM',
                    id: 'id1',
                    idKey: 'NOM ID',
                    coordinates: '10 100, -10 110, 10 120'
                },
                style: {
                    fillColor: '#5b973e',
                    fillOpacity: 0.15,
                    strokeColor: '#5b973e',
                    strokeWidth: 2
                }
            }

            component.filteredNominations = [nomWithAllTheTargets]

            const createPointSpy = mockMoW.FeatureFactory.createPointFeature.and.returnValue({feature: 1})
            createPolygonFeatureSpy.and.returnValues({feature: polygon})

            component.renderNominationCoordinates()

            expect(createPointSpy).toHaveBeenCalledWith(pointWithoutRadius)

            expect(createCircleFeatureSpy).toHaveBeenCalledWith(pointRadius)

            expect(addFeaturesSpy).toHaveBeenCalledTimes(3)
            expect(addFeaturesSpy).toHaveBeenCalledWith(2, {feature: 1})

            expect(createPolygonFeatureSpy).toHaveBeenCalledWith(polygon)
            expect(addFeaturesSpy).toHaveBeenCalledWith(2, {feature: polygon})
        })

        describe('point', () => {
            it('draws a point', () => {
                mockCoordinatesService.getLatLng.and.returnValues([30, -140])
                let point = {
                    point: [30, -140],
                    attributes: {
                        title: 'title1',
                        targetName: 'target 1',
                        justification: undefined,
                        status: 'TO_DO',
                        system: 'PRISM',
                        id: 'id1',
                        idKey: 'NOM ID',
                        coordinates: '30 -140'
                    },
                    style: {
                        pointRadius: 8,
                        fillColor: '#5b973e',
                        strokeColor: '#FFFFFF',
                        strokeWidth: 2,
                        fillOpacity: 0.8
                    }
                }

                component.filteredNominations = [nomPointWithTargetWithoutRadius]

                const createPointSpy = mockMoW.FeatureFactory.createPointFeature.and.returnValue({feature: 1})

                component.renderNominationCoordinates()

                expect(createPointSpy).toHaveBeenCalledWith(point)
                expect(addFeaturesSpy).toHaveBeenCalledWith(2, {feature: 1})
            })

            it('draws a point with radius', () => {
                mockCoordinatesService.getLatLng.and.returnValues([30, -140])
                const circle = {
                    attributes: {
                        title: 'title1',
                        targetName: 'target 1',
                        justification: undefined,
                        status: 'TO_DO',
                        system: 'PRISM',
                        id: 'id1',
                        idKey: 'NOM ID',
                        coordinates: '30 -140'
                    },
                    circle: {
                        center: {
                            lat: 30,
                            lon: -140
                        },
                        radius: 18.52
                    },
                    style: {
                        fillColor: '#5b973e',
                        fillOpacity: 0.15,
                        strokeColor: '#5b973e'
                    }
                }

                component.filteredNominations = [nomPointWithTargetWithRadius]

                const createCircleSpy = mockMoW.FeatureFactory.createCircleFeature.and.returnValue({feature: 1})
                const createPointSpy = mockMoW.FeatureFactory.createPointFeature.and.returnValue({feature: 1})

                component.renderNominationCoordinates()

                expect(createCircleSpy).toHaveBeenCalledWith(circle)
                expect(createPointSpy).not.toHaveBeenCalled()
                expect(addFeaturesSpy).toHaveBeenCalledWith(2, {feature: 1})
            })

            it('does not draw a point for invalid target coordinates', () => {
                mockCoordinatesService.getLatLng.and.returnValues(null)

                component.filteredNominations = [nomPointWithTargetWithoutRadius]

                const createPointSpy = mockMoW.FeatureFactory.createPointFeature.and.returnValue({feature: 1})

                component.renderNominationCoordinates()

                expect(createPointSpy).not.toHaveBeenCalled()
                expect(addFeaturesSpy).not.toHaveBeenCalled()
            })
        })

        describe('polygon', () => {
            it('draws a polygon', () => {
                mockCoordinatesService.getLatLng.and.returnValues([10, 100], [-10, 110], [10, 120])

                const polygon = {
                    polygon: [[10, 100], [-10, 110], [10, 120]],
                    attributes: {
                        title: 'title1',
                        targetName: 'target 1',
                        justification: undefined,
                        status: 'TO_DO',
                        system: 'PRISM',
                        id: 'id1',
                        idKey: 'NOM ID',
                        coordinates: '10 100, -10 110, 10 120'
                    },
                    style: {
                        fillColor: '#5b973e',
                        fillOpacity: 0.15,
                        strokeColor: '#5b973e',
                        strokeWidth: 2
                    }
                }

                component.filteredNominations = [nomPolygon]
                createPolygonFeatureSpy.and.returnValues({feature: polygon})

                component.renderNominationCoordinates()

                expect(createPolygonFeatureSpy).toHaveBeenCalledWith(polygon)
                expect(addFeaturesSpy).toHaveBeenCalledTimes(1)
                expect(addFeaturesSpy).toHaveBeenCalledWith(2, {feature: polygon})
                expect(createCircleFeatureSpy).not.toHaveBeenCalled()
            })

            it('does not draw a polygon for invalid target coordinates', () => {
                mockCoordinatesService.getLatLng.and.returnValues(null)

                const polygon = {
                    polygon: [[10, 100], [-10, 110], [10, 120]],
                    attributes: {
                        title: 'title1',
                        targetName: 'target 1',
                        justification: undefined,
                        status: 'TO_DO',
                        coordinates: '10 100, -10 110, 10 120'
                    },
                    style: {
                        fillColor: '#d79d3d',
                        fillOpacity: 0.15,
                        strokeColor: '#d79d3d',
                        strokeWidth: 2
                    }
                }

                component.filteredNominations = [nomPolygon]
                createPolygonFeatureSpy.and.returnValues({feature: polygon})

                component.renderNominationCoordinates()

                expect(createPolygonFeatureSpy).not.toHaveBeenCalledWith(polygon)
                expect(addFeaturesSpy).not.toHaveBeenCalledTimes(1)
                expect(addFeaturesSpy).not.toHaveBeenCalledWith(2, {feature: polygon})
                expect(createCircleFeatureSpy).not.toHaveBeenCalled()
            })
        })
    })

    describe('combined list', () => {
        it('is set correctly', () => {
            component.filteredRfis = [
                makeRfiListItem({id: 2, title: 'title2', justification: 'stuff', createdOn: '2018-1-1'}),
                makeRfiListItem({id: 1, title: 'title1', justification: 'stuff', createdOn: '2018-1-3'})]

            const expectedRfis = component.filteredRfis

            component.filteredNominations = [
                makeNomCrListItem({id: '1', title: 'prism title', status: 'To Do', createdOn: '2018-1-2'}),
                makeNomCrListItem({id: '2', title: 'prism title2', status: 'To Do', createdOn: '2018-1-4'})]

            const expectedNoms = component.filteredNominations

            component.filterRecords()

            expect(component.combinedList).toEqual([expectedNoms[1], expectedRfis[1], expectedNoms[0], expectedRfis[0]])
        })
    })
})