import {Component, Input, NgZone, OnChanges, OnInit} from '@angular/core'
import {Store} from '@ngrx/store'
import {AppState} from '../state/appstate'
import {RfiService} from '../services/rfi.service'
import {CoordinatesService} from '../services/coordinates.service'
import {PrismService} from '../services/prism.service'
import {Target} from '../models/target.model'
import {CloseSidebarAction} from '../state/actions'
import {CoordinateValidators} from '../validators/custom-validators'
import {MapService} from '../services/map-service.service'
import {RecordItem, RecordType} from '../models/record.model'

@Component({
    selector: 'stargate-map',
    templateUrl: './map.component.html',
    styleUrls: ['./map.component.scss']
})
export class MapComponent implements OnInit, OnChanges {
    @Input()
    activeTab: string

    centerLat = 0
    centerLng = 0
    filteredRfis: RecordItem[] = []
    filteredNominations: RecordItem[] = []
    combinedList: RecordItem[] = []
    map: any
    MoW: any
    featureOverlay: any
    loading: boolean = true

    activeRecordIds: string[] = []

    constrainResults: boolean = false
    currentBoundingBox: number[] = [-180, -90, 180, 90]

    constructor(
        private store: Store<AppState>,
        private rfiService: RfiService,
        private prismService: PrismService,
        private coordinatesService: CoordinatesService,
        private mapService: MapService,
        private ngZone: NgZone) {

        this.store.select('filteredRfis').subscribe(filteredRfis => {
            this.filteredRfis = (filteredRfis) ? filteredRfis : []
            this.updateFilteredList()
        })

        this.store.select('filteredNominations').subscribe(filteredNominations => {
            this.filteredNominations = (filteredNominations) ? filteredNominations : []
            this.updateFilteredList()
        })

        window.addEventListener('mapDetailsLink', (event: CustomEvent) => {
            if (event.detail && event.detail.system === 'IMM') {
                this.rfiService.fetchRfi(event.detail.id)
            } else if (event.detail && event.detail.system === 'PRISM') {
                this.prismService.fetchNominationById(event.detail.id)
            }
        })
    }

    ngOnInit(): void {
        //This method is untested. Tread lightly!
        this.mapService.ready((mow) => {
            this.MoW = mow
            if (this.activeTab === 'MAP') {
                this.mapSetup()
            }
        })
    }

    ngOnChanges(changes) {
        if (this.loading && this.activeTab === 'MAP' && this.MoW) {
            this.mapSetup()
            window.dispatchEvent(new Event('resize'))
        }
    }

    mapSetup() {
        this.map = this.mapService.createMap()
        this.map.ready(() => {
            this.initializeMap()
            this.map.setBasemap(this.MoW.Basemap.ID.CANVAS_SLATE)
            this.renderRfiCoordinates()
            this.renderNominationCoordinates()

            this.map.events.subscribe(this.MoW.Events.ID.FEATURE_SELECT, (event, data) => {
                this.ngZone.run(() => {
                    if (!this.activeRecordIds.includes(data.feature.attributes.id.toString())) {
                        this.activeRecordIds = [...this.activeRecordIds, data.feature.attributes.id.toString()]
                    }
                })
            })

            this.map.events.subscribe(this.MoW.Events.ID.MAP_CLICK, (event, data) => {
                this.ngZone.run(() => {
                    this.activeRecordIds = []
                    this.store.dispatch(new CloseSidebarAction())
                })
            })

            this.map.events.subscribe(this.MoW.Events.ID.MAP_MOVE, (event, data) => {
                this.currentBoundingBox = data.boundingBox
                this.filterRecords()
            })

            this.ngZone.run(() => {
                this.loading = false
            })
        })
    }

    renderRfiCoordinates() {
        this.filteredRfis.forEach(rfi => {
            if (!!rfi.targets) {
                const hasTargets = rfi.targets
                    .filter(target => target.coordinates && target.coordinates !== '')
                    .map(target => {
                        const coordinates = CoordinateValidators.separateCoordinates(target.coordinates)
                        switch (target.type) {
                            case 'POINT':
                                return this.drawPoint(coordinates[0], rfi, RecordType.RFI, '#d79d3d', target)
                            case 'LOC':
                                return this.drawLine(coordinates, rfi, RecordType.RFI, '#d79d3d', target)
                            case 'DSA':
                            case 'AREA':
                                this.drawPolygon(coordinates, rfi, RecordType.RFI, '#d79d3d', target)
                                return true
                            default:
                                return false
                        }
                    }).length > 0
                if (!hasTargets && rfi.coordinates) {
                    this.drawPoint(rfi.coordinates, rfi, RecordType.RFI, '#d79d3d')
                }
            } else if (rfi.coordinates) {
                this.drawPoint(rfi.coordinates, rfi, RecordType.RFI, '#d79d3d')
            }
        })
    }

    renderNominationCoordinates() {
        this.filteredNominations.filter(nom => !!nom.targets && nom.targets.length > 0).map(nom => {
            nom.targets.forEach(target => {
                const coordinatesArray = target.coordinates.split(', ').filter(p => !!p)

                // plot the first coordinate as a point if there are less than 3
                switch (coordinatesArray.length) {
                    case 0: break
                    case 1: this.drawPoint(coordinatesArray[0], nom, RecordType.NOMINATION, '#5b973e', target)
                        break
                    case 2: this.drawLine(coordinatesArray, nom, RecordType.NOMINATION, '#5b973e', target)
                        break
                    case 3:
                    default: this.drawPolygon(coordinatesArray, nom, RecordType.NOMINATION, '#5b973e', target)
                }
            })
        })
    }

    initializeMap() {
        this.featureOverlay = this.mapService.createIMMOverlay(this.MoW)
        this.map.setCenter([this.centerLat, this.centerLng], 0)
        this.map.addOverlay(this.featureOverlay)
    }

    revealLocation(recordId: number | string) {
        const strRecordId = recordId.toString()
        if (this.activeRecordIds.includes(strRecordId)) {
            return
        }

        this.map.unselectAllFeatures()
        this.activeRecordIds = []
        const selectedFeatures = this.map.getFeatures(this.featureOverlay.id).filter(feature => feature.attributes.id === strRecordId)
        if (selectedFeatures && selectedFeatures.length > 0) {
            const currentZoom = this.map.getZoom()
            let obj = {};
            obj[this.featureOverlay.id] = []
            selectedFeatures.forEach(feature => {
                this.map.setFeatureSelected(this.featureOverlay.id, feature.id, true)
                obj[this.featureOverlay.id].push(feature.id)
            })
            this.map.setExtent(this.map.getFeatureExtentAcrossLayers(obj));
            this.map.setZoom(currentZoom)
        }
    }

    doConstrainResults(val: boolean) {
        this.constrainResults = val
        this.filterRecords()
    }

    filterRecords() {
        if (!this.constrainResults) {
            this.combinedList = this.filteredRfis
                .concat(this.filteredNominations).sort((record1, record2) => {
                    return Date.parse(record2.createdOn) - Date.parse(record1.createdOn)
                })
            return
        }

        this.map.performIdentify(this.currentBoundingBox, [this.featureOverlay.id], (overlays) => {
            let preSortedRecordList: RecordItem[] = []

            overlays[this.featureOverlay.id].forEach(feature => {
                let record = this.filteredRfis.find(filteredRfi => filteredRfi.id.toString() === feature.attributes.id)
                if (!record) {
                    record = this.filteredNominations.find(filteredNom => filteredNom.id === feature.attributes.id)
                }

                if (!preSortedRecordList.find(existingRecord => existingRecord.id === record.id)) {
                    preSortedRecordList.push(record)
                }
            })

            this.ngZone.run(() => {
                this.combinedList = preSortedRecordList.sort((record1, record2) => {
                    return Date.parse(record2.createdOn) - Date.parse(record1.createdOn)
                })
            })
        })

    }

    private drawPoint(coordinates, record: RecordItem, recordType: RecordType, color, target?: Target) {
        let latLong = this.coordinatesService.getLatLng(coordinates)
        if (!latLong) {
            console.warn('Invalid coordinates - not drawing the point for record: ' + record.id)
            return false
        }

        if (!!latLong) {
            const attributes = {
                title: record.title,
                targetName: target ? target.name : '',
                justification: record.justification,
                status: record.status,
                system: recordType === RecordType.RFI ? 'IMM' : 'PRISM',
                id: record.id.toString(),
                idKey: recordType === RecordType.RFI ? 'ID' : 'NOM ID',
                coordinates: `${latLong[0]} ${latLong[1]}`
            }


            const radius = MapComponent.getRadius(target)
            if (radius != 0) {
                const circleProperties = {
                    attributes,
                    circle: {
                        center: {
                            lat: latLong[0],
                            lon: latLong[1]
                        },
                        radius: radius
                    },
                    style: {
                        fillColor: color,
                        fillOpacity: 0.15,
                        strokeColor: color
                    }
                }
                const circleFeature = this.MoW.FeatureFactory.createCircleFeature(circleProperties)
                this.map.addFeatures(this.featureOverlay.id, circleFeature)
            } else {
                const pointProperties = {
                    attributes,
                    point: [latLong[0], latLong[1]],
                    style: {
                        pointRadius: 8,
                        fillColor: color,
                        strokeColor: '#FFFFFF',
                        strokeWidth: 2,
                        fillOpacity: 0.8
                    }
                }
                const pointFeature = this.MoW.FeatureFactory.createPointFeature(pointProperties)
                this.map.addFeatures(this.featureOverlay.id, pointFeature)
            }
        }
        return true
    }

    private drawLine(coordinates, record, recordType, color, target: Target) {
        const linePoints = coordinates.map(coordinate => this.coordinatesService.getLatLng(coordinate))
        if (linePoints.includes(null)) {
            console.warn('Invalid coordinates - not drawing the line for record: ' + record.id)
            return
        }

        const lineFeatureProperties = {
            line: linePoints,
            attributes: {
                title: record.title,
                targetName: target ? target.name : '',
                justification: record.justification,
                status: record.status,
                system: recordType === RecordType.RFI ? 'IMM' : 'PRISM',
                id: record.id.toString(),
                idKey: recordType === RecordType.RFI ? 'ID' : 'NOM ID',
                coordinates: linePoints.map(point => point.join(' ')).join(', ')
            },
            style: {
                fillColor: color,
                fillOpacity: 0.15,
                strokeColor: color,
                strokeWidth: 2
            }
        }

        const lineFeature = this.MoW.FeatureFactory.createLineFeature(lineFeatureProperties)
        this.map.addFeatures(this.featureOverlay.id, lineFeature)
    }

    private drawPolygon(coordinates, record, recordType: RecordType, color, target: Target) {
        const polyPoints = coordinates.map(coordinate => this.coordinatesService.getLatLng(coordinate))
        if (polyPoints.includes(null)) {
            console.warn('Invalid coordinates - not drawing the polygon for record: ' + record.id)
            return
        }

        const polygonFeatureProperties = {
            polygon: polyPoints,
            attributes: {
                title: record.title,
                targetName: target ? target.name : '',
                justification: record.justification,
                status: record.status,
                system: recordType === RecordType.RFI ? 'IMM' : 'PRISM',
                id: record.id.toString(),
                idKey: recordType === RecordType.RFI ? 'ID' : 'NOM ID',
                coordinates: polyPoints.map(point => point.join(' ')).join(', ')
            },
            style: {
                fillColor: color,
                fillOpacity: 0.15,
                strokeColor: color,
                strokeWidth: 2
            }
        }

        const polygonFeature = this.MoW.FeatureFactory.createPolygonFeature(polygonFeatureProperties)
        this.map.addFeatures(this.featureOverlay.id, polygonFeature)
    }

    private updateFilteredList() {
        if (this.map && this.featureOverlay) {
            this.map.clearFeatureLayer(this.featureOverlay.id)
            this.renderRfiCoordinates()
            this.renderNominationCoordinates()
        }

        this.filterRecords()
    }

    private static getRadius(target: Target) {
        if (!!target) {
            if (!!target.radius) {
                if (target.radiusUnit === 'NM') {
                    return target.radius * 1.852
                }
                return target.radius
            }
        }
        return 0
    }
}

