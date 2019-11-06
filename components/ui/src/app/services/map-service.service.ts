import {WindowService} from '../window/window.service'
import {Injectable} from '@angular/core'
import {HttpClient} from "@angular/common/http";

interface MapUrlResponse {
    url: string
}

@Injectable()
export class MapService {
    window: Window
    private MoW: any
    private basePath = '/api/v1/map_url'

    constructor(windowService: WindowService, private http: HttpClient) {
        this.window = windowService.nativeWindow
    }

    ready(onReady: (mow: any) => void) {
        this.http.get(this.basePath).subscribe((response: MapUrlResponse) => {
            const mapOfTheWorldScript = document.createElement('script');
            mapOfTheWorldScript.setAttribute('src', `https://${response.url}/api/mow.js`)
            mapOfTheWorldScript.setAttribute('type', 'application/javascript')
            mapOfTheWorldScript.onload = () => {
                this.window.MoW.ready(() => {
                    this.MoW = this.window.MoW
                    onReady(this.MoW)
                })
            }
            document.body.appendChild(mapOfTheWorldScript)
        })
    }

    createMap(): any {
        return new this.MoW.Map({target: 'the-map'})
    }

    createIMMOverlay(mow: any): any {
        const overlayTitle = 'IMM Overlay'
        const clickTemplate = '<p><strong>Title:</strong> {{title}}</p>' +
            '<p><strong>Target Name:</strong> {{targetName}}</p>' +
            '<p><strong>Justification:</strong> {{justification}}</p>' +
            '<p><strong>Status:</strong> {{status}}</p>' +
            '<p><strong>Originating System:</strong> {{system}}</p>' +
            '<p><strong>{{idKey}}:</strong> {{id}}</p>' +
            '<p><strong>Coordinates:</strong> {{coordinates}}</p>' +
            '<p><a onclick="window.dispatchEvent(new CustomEvent(\'mapDetailsLink\', { detail: { id: \'{{id}}\', system: \'{{system}}\' } }))">View Details</a></p>'

        const defaultStyle = {}
        const highlightStyle = {
            fill: {
                color: '#2721CF',
                opacity: .8
            },
            stroke: {
                color: '#2721CF'
            },
            text: {
                label: '${title}\n${targetName}',
                labelAlign: 'r',
                size: 12,
                color: '#FFFFFF',
                outline: {
                    color: '#1f2226',
                    width: 2
                }
            }
        }

        const vectorStyle = new mow.VectorStyle(defaultStyle, highlightStyle)
        const protocol = new mow.protocols.VectorProtocol('', mow.protocols.VectorProtocol.TYPE.FEATURE,
            {}, {}, new mow.Strategies(), vectorStyle, null, {})


        return new mow.Overlay(overlayTitle, protocol, overlayTitle,
            new mow.LayerContext(), true, clickTemplate, '{{title}}')
    }
}